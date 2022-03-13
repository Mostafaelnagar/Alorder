package app.grand.tafwak.domain.profile.use_case

import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.AvatarResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProfileUseCase @Inject constructor(
  private val profileRepository: ProfileRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke(): Flow<Resource<BaseResponse<ArrayList<AvatarResponse>>>> = flow {
    emit(Resource.Loading)
    val result = profileRepository.avatarList()
    emit(result)
  }.flowOn(Dispatchers.IO)

  operator fun invoke(
    request: UpdateProfileRequest
  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
    if (checkValidation(request)) {
      emit(Resource.Loading)
      val result = profileRepository.updateProfile(request)
      if (result is Resource.Success) {
        userLocalUseCase.saveUserToken(result.value.data.token)
      }
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: UpdateProfileRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.accountType == Constants.TEACHER_TYPE && request.nickname.isEmpty()) {
      request.validation.nickNameError.set(Constants.EMPTY)
      isValid = false
    }

    if (request.email.isEmpty()) {
      request.validation.emailError.set(Constants.EMPTY)
      isValid = false
    }
    if (!request.email.isValidEmail()) {
      request.validation.emailError.set(Constants.INVALID_EMAIL)
      isValid = false
    }
    if (request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    return isValid
  }
}