package com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.login.domain.models.GoogleSignInResult
import kotlinx.coroutines.flow.Flow

class GoogleSignInUseCase(private val context: Context) : BaseUseCase<Flow<Resource<GoogleSignInResult>>, Int>() {
    override suspend fun invoke(body: Int): Flow<Resource<GoogleSignInResult>> = flowExecute {
        val credentialManager = CredentialManager.create(context)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(body))
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val result = credentialManager.getCredential(context, request)
        val credential = GoogleIdTokenCredential.createFrom(result.credential.data)
        GoogleSignInResult(
            email = credential.email.orEmpty(),
            name = credential.displayName.orEmpty(),
            socialId = credential.uniqueId
        )
    }
}