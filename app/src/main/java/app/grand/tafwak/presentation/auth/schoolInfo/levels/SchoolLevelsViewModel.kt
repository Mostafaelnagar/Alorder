package app.grand.tafwak.presentation.auth.schoolInfo.levels

import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.educational.entity.model.Grade
import app.grand.tafwak.domain.educational.use_case.EducationalUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.auth.schoolInfo.entity.UpdateEducationRequest
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.auth.schoolInfo.levels.adapters.GradesAdapter
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolLevelsViewModel @Inject constructor(
  private val educationalUseCase: EducationalUseCase,
  savedStateHandle: SavedStateHandle,
  private val userLocalUseCase: UserLocalUseCase
) :
  BaseViewModel() {
  private val _gradeResponse =
    MutableStateFlow<Resource<BaseResponse<List<Grade>>>>(Resource.Default)
  val gradeResponse = _gradeResponse
  private val _registerResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val registerResponse = _registerResponse

  var stageId = 0

  @Bindable
  var isLogged = false

  @Bindable
  val adapter: GradesAdapter = GradesAdapter()

  init {
    savedStateHandle.get<Int>("educational_stage_id")?.let { stage_id ->
      stageId = stage_id
      getGrades(stage_id)
    }
    checkIfLogged()
  }

  private fun checkIfLogged() {
    viewModelScope.launch {
      userLocalUseCase.invoke().collect { user ->
        if (user.accountType.isNotEmpty())
          isLogged = true
        notifyPropertyChanged(BR.isLogged)
      }
    }
  }

  private fun getGrades(stage_id: Int) {
    educationalUseCase.invoke(stage_id)
      .onEach { result ->
        _gradeResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun registerStep4() {
    if (adapter.lastSelected != -1) {
      if (!isLogged)
        educationalUseCase.registerStep4(adapter.lastSelected)
          .onEach { result ->
            _registerResponse.value = result
          }
          .launchIn(viewModelScope)
      else
        educationalUseCase.updateEducation(grade_id = adapter.lastSelected, stage_id =stageId)
          .onEach { result ->
            _registerResponse.value = result
          }
          .launchIn(viewModelScope)
    }
  }

  fun updateAdapter(stages: List<Grade>) {
    adapter.differ.submitList(stages)
    notifyPropertyChanged(BR.adapter)
  }
}