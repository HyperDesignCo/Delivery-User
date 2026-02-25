package com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.SocialLoginRequest
import kotlinx.coroutines.flow.Flow

class SocialLoginUseCase(private val repository: IAuthenticationRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, SocialLoginRequest>() {
    override suspend fun invoke(body: SocialLoginRequest): Flow<Resource<Authentication>> =
        flowExecute { repository.socialLogin(body) }
}