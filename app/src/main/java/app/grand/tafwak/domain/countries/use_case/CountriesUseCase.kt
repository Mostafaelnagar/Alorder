package app.grand.tafwak.domain.countries.use_case

import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.request.UpdateCountryRequest
import app.grand.tafwak.domain.countries.entity.Country
import app.grand.tafwak.domain.countries.entity.request.RegisterStep2
import app.grand.tafwak.domain.countries.repository.CountriesRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CountriesUseCase @Inject constructor(
  private val countriesRepository: CountriesRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
  operator fun invoke(): Flow<Resource<BaseResponse<List<Country>>>> =
    flow {
      emit(Resource.Loading)
      val result = countriesRepository.countries()
      emit(result)
    }.flowOn(Dispatchers.IO)

  fun registerStep2(country_id: Int): Flow<Resource<BaseResponse<*>>> =
    flow {
      emit(Resource.Loading)
      val result = countriesRepository.registerStep2(RegisterStep2(2, country_id))
      if (result is Resource.Success) {
        userLocalUseCase.saveCountryId(country_id.toString())
        userLocalUseCase.registerStep("3")
      }
      emit(result)
    }.flowOn(Dispatchers.IO)

  fun updateCountry(country_id: Int): Flow<Resource<BaseResponse<*>>> =
    flow {
      emit(Resource.Loading)
      val result = countriesRepository.updateCountry(UpdateCountryRequest(country_id))
      if (result is Resource.Success) {
        userLocalUseCase.saveCountryId(country_id.toString())
      }
      emit(result)
    }.flowOn(Dispatchers.IO)

}