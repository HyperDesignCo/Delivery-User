package com.example.delivaryUser.common.domain.usecase

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<out Domain, in Body> {
    abstract suspend operator fun invoke(body: Body): Domain
    fun <Domain> flowExecute(codeBlock: suspend () -> Domain) = channelFlow {
        send(Resource.Success(codeBlock.invoke()))
        send(Resource.Loading(isLoading = false))
    }.onStart {
        emit(Resource.Loading(isLoading = true))
    }.catch { exception ->
        emit(handleFailure(exception))
        emit(Resource.Loading(isLoading = false))
    }.flowOn(Dispatchers.IO)

    suspend fun <Domain> nonFlowExecute(codeBlock: suspend () -> Domain) = runCatching {
        val result = codeBlock.invoke()
        Resource.Success(result)
    }.getOrElse(::handleFailure)

    private fun handleFailure(exception: Throwable): Resource.Failure {
        val failureException =
            exception as? DelivaryUserException ?: DelivaryUserException.UnKnown(message = "Unknown Error $exception")
        return Resource.Failure(failureException)
    }
}