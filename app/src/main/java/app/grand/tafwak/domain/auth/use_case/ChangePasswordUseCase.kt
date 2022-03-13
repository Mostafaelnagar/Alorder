package app.grand.tafwak.domain.auth.use_case

import app.grand.tafwak.domain.auth.entity.request.*
import app.grand.tafwak.domain.auth.enums.AuthFieldsValidation
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.profile.entity.UpdatePassword
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ChangePasswordUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  @Throws(ChangePasswordValidationException::class)
  operator fun invoke(request: ChangePasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (request.password.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_PASSWORD.value.toString())
    }

    if (request.password_confirmation.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_CONFIRM_PASSWORD.value.toString())
    }

    if (request.password_confirmation != request.password) {
      throw RegisterValidationException(AuthFieldsValidation.PASSWORD_NOT_MATCH.value.toString())
    }
    emit(Resource.Loading)
    val result = authRepository.changePassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

  operator fun invoke(request: UpdatePassword): Flow<Resource<BaseResponse<*>>> = flow {
    if (checkUpdatePasswordValidation(request)) {
      if (request.new_password_confirmation != request.new_password) {
        emit(Resource.Failure(FailureStatus.OTHER, Constants.NOT_MATCH_PASSWORD, null))
      } else {
        emit(Resource.Loading)
        val result = authRepository.updatePassword(request)
        emit(result)
      }
    }
  }.flowOn(Dispatchers.IO)

  private fun checkUpdatePasswordValidation(request: UpdatePassword): Boolean {
    var isValid = true
    if (request.old_password.isEmpty()) {
      request.validation.oldPasswordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.new_password.isEmpty()) {
      request.validation.newPasswordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.new_password_confirmation.isEmpty()) {
      request.validation.newPasswordConfirmError.set(Constants.EMPTY)
      isValid = false
    }
    return isValid
  }
}