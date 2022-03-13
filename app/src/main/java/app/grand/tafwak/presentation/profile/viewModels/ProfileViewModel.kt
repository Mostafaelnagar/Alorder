package app.grand.tafwak.presentation.profile.viewModels

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.SendFirebaseTokenUseCase
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.general.use_case.GeneralUseCases
import app.grand.tafwak.domain.profile.entity.AvatarResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.profile.use_case.ProfileUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.presentation.profile.adapter.AvatarAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
  private val profileUseCase: ProfileUseCase
) :
  BaseViewModel() {
  @Bindable
  val request = UpdateProfileRequest()
  private val _profileResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val profileResponse = _profileResponse
  private val _avatarResponse =
    MutableStateFlow<Resource<BaseResponse<ArrayList<AvatarResponse>>>>(Resource.Default)
  val avatarResponse = _avatarResponse

  @Bindable
  val avatarAdapter = AvatarAdapter()

  init {
    viewModelScope.launch {

      userLocalUseCase.invoke().collect { userLocal ->
        request.name = userLocal.name
        request.email = userLocal.email
        request.phone = userLocal.phone
        request.teacherImage = userLocal.image
        request.accountType = userLocal.accountType
        request.nickname = userLocal.nickname
        if (userLocal.accountType.equals(Constants.STUDENT_TYPE))
          avatarList()

        notifyPropertyChanged(BR.request)
      }
    }

  }

  fun updateProfile() {
    profileUseCase.invoke(request).catch { exception ->
      Log.e(
        "updateProfile",
        "updateProfile: ${exception.printStackTrace()}"
      )
    }.onEach { result ->
      _profileResponse.value = result
    }.launchIn(viewModelScope)
  }

  fun avatarList() {
    profileUseCase.invoke().catch { exception ->
      Log.e(
        "updateProfile",
        "updateProfile: ${exception.printStackTrace()}"
      )
    }.onEach { result ->
      _avatarResponse.value = result
    }.launchIn(viewModelScope)
  }

  fun updateAvatarAdapter(avatarList: ArrayList<AvatarResponse>) {
    avatarAdapter.differ.submitList(avatarList)
    notifyPropertyChanged(BR.avatarAdapter)
  }
}