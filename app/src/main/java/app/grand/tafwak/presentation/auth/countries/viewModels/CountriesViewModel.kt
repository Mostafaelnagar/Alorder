package app.grand.tafwak.presentation.auth.countries.viewModels

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.countries.entity.Country
import app.grand.tafwak.domain.countries.use_case.CountriesUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.auth.countries.adapters.CountriesAdapter
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
  private val countriesUseCase: CountriesUseCase,
  private val userLocalUseCase: UserLocalUseCase
) :
  BaseViewModel() {
  private val _countriesPasswordResponse =
    MutableStateFlow<Resource<BaseResponse<List<Country>>>>(Resource.Default)
  val countriesPasswordResponse = _countriesPasswordResponse
  private val _registerResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val registerResponse = _registerResponse

  @Bindable
  var isLogged = false

  @Bindable
  val adapter: CountriesAdapter = CountriesAdapter()

  init {
    getCountries()
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

  private fun getCountries() {
    countriesUseCase.invoke()
      .onEach { result ->
        _countriesPasswordResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun registerStep2() {
    if (adapter.lastSelected != -1) {
      if (!isLogged)
        countriesUseCase.registerStep2(adapter.lastSelected)
          .onEach { result ->
            _registerResponse.value = result
          }
          .launchIn(viewModelScope)
      else
        countriesUseCase.updateCountry(adapter.lastSelected)
          .onEach { result ->
            _registerResponse.value = result
          }
          .launchIn(viewModelScope)
    }
  }

  fun updateAdapter(countries: List<Country>) {
    adapter.differ.submitList(countries)
    notifyPropertyChanged(BR.adapter)
  }

}