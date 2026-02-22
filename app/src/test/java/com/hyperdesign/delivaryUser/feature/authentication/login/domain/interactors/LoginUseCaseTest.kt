package com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.feature.authentication.base.data.repository.AuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.login.data.AuthenticationDtoMockups
import com.hyperdesign.delivaryUser.feature.authentication.login.data.AuthenticationDtoMockups.validLogin
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/** Test cases for [LoginUC]
 * - Given Correct Phone and Password then Return Login Success
 * - Given Correct Phone and Password but Network Failure then Return Network Failure Email test Cases
 * - Given Empty Phone then return Validation Failure
 * - Given Phone Longer Than 50 Characters then Return Validation Failure Password test Cases
 * - Given Empty Password then Return Validation Failure
 * - Given Password Shorter Than 8 Characters then Return Validation Failure
 */
class LoginUseCaseTest {
    private val remoteDS = mockk<IAuthenticationRemoteDataSource>()
    private val localDS = mockk<IUserLocalDataSource>()

    private lateinit var useCase: LoginUseCase

    @Before
    fun setup() {
        val repository = AuthenticationRepository(remote = remoteDS, local = localDS)
        useCase = LoginUseCase(repository = repository)
    }

    @Test
    fun givenValidPhoneAndPassword_whenLogin_thenReturnSuccess() = runTest {
        // Given
        val request = AuthenticationDtoMockups.validLoginRequest
        val expectedResponse = AuthenticationDtoMockups.validLoginDto
        coEvery { remoteDS.login(request) } returns expectedResponse
        coEvery { localDS.saveUser(any()) } just Runs
        // When
        val result = useCase.invoke(AuthenticationDtoMockups.validLoginRequest).drop(1).first()
        // Then
        assertEquals(Resource.Success(validLogin), result)
        coVerify(exactly = 1) { remoteDS.login(request) }
    }

    @Test
    fun givenRemoteFailure_whenLogin_thenNetworkFailure() = runTest {
        // Given
        val error = DelivaryUserException.Network.Repeatable("network error")
        val request = AuthenticationDtoMockups.validLoginRequest
        coEvery { remoteDS.login(request) } throws error
        coEvery { localDS.saveUser(any()) } just Runs
        // When
        val result = useCase.invoke(request)
            .drop(1)
            .first()
        // Then
        assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        assertTrue(failure.exception is DelivaryUserException.Network.Repeatable)
        assertEquals("network error", failure.exception.message)
        coVerify(exactly = 1) { remoteDS.login(request) }
    }

    @Test
    fun givenEmptyPhone_whenLogin_thenReturnValidationFailure() = runTest {
        // Given
        val request = AuthenticationDtoMockups.validLoginRequest.copy(phone = "")
        // When
        val result = useCase.invoke(request)
            .drop(1)
            .first()
        // Then
        assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        assertTrue(failure.exception is DelivaryUserException.Local.RequestValidation)
        // verify
        coVerify(exactly = 0) { remoteDS.login(any()) }
        coVerify(exactly = 0) { localDS.saveUser(any()) }
    }

    @Test
    fun givenPhoneLongerThan50Characters_whenLogin_thenReturnValidationFailure() = runTest {
        // Given
        val longPhone = "1".repeat(51)
        val request = AuthenticationDtoMockups.validLoginRequest.copy(phone = longPhone)
        // When
        val result = useCase.invoke(request)
            .drop(1)
            .first()
        // Then
        assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        assertTrue(failure.exception is DelivaryUserException.Local.RequestValidation)
        //verify
        coVerify(exactly = 0) { remoteDS.login(any()) }
        coVerify(exactly = 0) { localDS.saveUser(any()) }
    }

    @Test
    fun givenEmptyPassword_whenLogin_thenReturnValidationFailure() = runTest {
        // Given
        val request = AuthenticationDtoMockups.validLoginRequest.copy(password = "")
        // When
        val result = useCase.invoke(request)
            .drop(1)
            .first()
        // Then
        assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        assertTrue(failure.exception is DelivaryUserException.Local.RequestValidation)
        //verify
        coVerify(exactly = 0) { remoteDS.login(any()) }
        coVerify(exactly = 0) { localDS.saveUser(any()) }
    }

    @Test
    fun givenPasswordShorterThan8Characters_whenLogin_thenReturnValidationFailure() = runTest {
        // Given
        val request = AuthenticationDtoMockups.validLoginRequest.copy(password = "Ab1!")
        // When
        val result = useCase.invoke(request)
            .drop(1)
            .first()
        // Then
        assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        assertTrue(failure.exception is DelivaryUserException.Local.RequestValidation)
        //verify
        coVerify(exactly = 0) { remoteDS.login(any()) }
        coVerify(exactly = 0) { localDS.saveUser(any()) }
    }
}