package app.grand.tafwak.presentation.profile.changePassword

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.auth.entity.request.ChangePasswordRequest
import app.grand.tafwak.domain.auth.use_case.ChangePasswordUseCase
import app.grand.tafwak.domain.profile.entity.UpdatePassword
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
  private val changePasswordUseCase: ChangePasswordUseCase
) : BaseViewModel() {
  var request = UpdatePassword()
  private val _changePasswordResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val changePasswordResponse = _changePasswordResponse
  var validationException = SingleLiveEvent<Int>()

  fun changePassword() {
//    changePasswordUseCase.invoke(request)
//      .catch { exception -> Log.e("changePassword", "changePassword: "+exception.message)}
//      .onEach { result ->
//        _changePasswordResponse.value = result
//      }
//      .launchIn(viewModelScope)
    clickEvent.value = Constants.CONFIRM_CODE
  }

}