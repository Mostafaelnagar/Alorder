package app.grand.tafwak.domain.countries.repository

import app.grand.tafwak.domain.auth.entity.request.UpdateCountryRequest
import app.grand.tafwak.domain.countries.entity.Country
import app.grand.tafwak.domain.countries.entity.request.RegisterStep2
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface CountriesRepository {
  suspend fun countries(): Resource<BaseResponse<List<Country>>>
  suspend fun registerStep2(registerStep2: RegisterStep2): Resource<BaseResponse<*>>
  suspend fun updateCountry(updateCountryRequest: UpdateCountryRequest): Resource<BaseResponse<*>>

}