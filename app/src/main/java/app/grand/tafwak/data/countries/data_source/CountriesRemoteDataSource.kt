package app.grand.tafwak.data.countries.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import app.grand.tafwak.domain.auth.entity.request.UpdateCountryRequest
import app.grand.tafwak.domain.countries.entity.request.RegisterStep2
import javax.inject.Inject

class CountriesRemoteDataSource @Inject constructor(private val apiService: CountriesServices) :
  BaseRemoteDataSource() {

  suspend fun countries() = safeApiCall {
    apiService.countries()
  }

  suspend fun registerStep2(registerStep3: RegisterStep2) = safeApiCall {
    apiService.registerStep2(registerStep3)
  }

  suspend fun updateCountry(updateCountryRequest: UpdateCountryRequest) = safeApiCall {
    apiService.updateCountry(updateCountryRequest)
  }
}