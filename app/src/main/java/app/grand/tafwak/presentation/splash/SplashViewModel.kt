package app.grand.tafwak.presentation.splash

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.general.use_case.GeneralUseCases
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
  BaseViewModel() {

}