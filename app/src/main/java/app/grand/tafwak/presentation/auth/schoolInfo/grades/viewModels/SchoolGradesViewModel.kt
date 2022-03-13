package app.grand.tafwak.presentation.auth.schoolInfo.grades.viewModels

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.educational.entity.model.Stage
import app.grand.tafwak.domain.educational.use_case.EducationalUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.auth.schoolInfo.grades.adapters.StagesAdapter
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.base.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolGradesViewModel @Inject constructor(
  private val educationalUseCase: EducationalUseCase,
  private val userLocalUseCase: UserLocalUseCase
) :
  BaseViewModel() {
  private val _stagesResponse =
    MutableStateFlow<Resource<BaseResponse<List<Stage>>>>(Resource.Default)
  val stagesResponse = _stagesResponse
  private val _registerResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val registerResponse = _registerResponse

  @Bindable
  var isLogged = false

  @Bindable
  val adapter: StagesAdapter = StagesAdapter()

  init {
    getStages()
    checkIfLogged()
  }

  private fun checkIfLogged() {
    viewModelScope.launch {
      userLocalUseCase.invoke().collect { user ->
        if (user.accountType.isNotEmpty()) {
          isLogged = true
        }
        notifyPropertyChanged(BR.isLogged)
      }
    }
  }

  private fun getStages() {
    educationalUseCase.invoke()
      .onEach { result ->
        _stagesResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun registerStep3() {
    if (adapter.lastSelected != -1) {
      if (!isLogged) {
        educationalUseCase.registerStep3(adapter.lastSelected)
          .onEach { result ->
            _registerResponse.value = result
          }
          .launchIn(viewModelScope)
      } else
        clickEvent.value = Constants.EDUCATIONS
    }
  }

  fun updateAdapter(stages: List<Stage>) {
    adapter.differ.submitList(stages)
    notifyPropertyChanged(BR.adapter)
  }

}