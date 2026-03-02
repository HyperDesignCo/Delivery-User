package com.hyperdesign.delivaryUser.service.googlesignin

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.feature.authentication.login.domain.models.GoogleSignInResult

class GoogleSignInManager(private val context: Context) {
    suspend fun signIn(): GoogleSignInResult {
        val credentialManager = CredentialManager.create(context)
        val googleSignInOption = GetSignInWithGoogleOption
            .Builder(serverClientId = context.getString(R.string.default_web_client_id))
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleSignInOption)
            .build()
        val result = credentialManager.getCredential(context, request)
        val credential = GoogleIdTokenCredential.createFrom(result.credential.data)
        return GoogleSignInResult(
            email = credential.email.orEmpty(),
            name = credential.displayName.orEmpty(),
            socialId = credential.uniqueId
        )
    }
}