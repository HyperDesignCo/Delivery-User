package com.example.delivary_user_android.common.data

import com.example.delivary_user_android.common.domain.exceptions.IErrorKeyEnum
import com.example.delivary_user_android.common.domain.exceptions.RequestErrorKeyValues

sealed class DelivaryUserException (override val message: String? = null): Exception(message) {
    sealed class Network(override val message: String? = null) : DelivaryUserException(message) {
        data class Repeatable(override val message: String? = null) : Network(message)
        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Network(message = "Network Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    sealed class Client(override val message: String?) : DelivaryUserException(message) {
        data class UnAuthorized(override val message: String? = null) : Client(message = "Unauthorized")
        data class ResponseValidation(
            val errors: Map<IErrorKeyEnum, String>,
            override val message: String? = null
        ) : Client(message)

        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Client(message = "Client Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    sealed class Server(message: String?) : DelivaryUserException(message) {
        data class InternalServerError(
            override val message: String? = null,
            val httpErrorCode: Int
        ) : Server(message = "Internal server error with code:${httpErrorCode}, and the failure reason: $message")
    }

    sealed class Local(message: String?) : DelivaryUserException(message) {
        data class RequestValidation(
            override val message: String? = null,
            val errors: Map<IErrorKeyEnum, RequestErrorKeyValues> = hashMapOf(),
        ) : Local(message)

        data class IOOperation(override val message: String? = null) :
            Local(message)

        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Local(message = "Local Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    data class UnKnown(override val message: String? = null) : DelivaryUserException(message)
}