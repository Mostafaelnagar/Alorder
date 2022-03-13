package app.grand.tafwak.domain.home.use_case

import android.util.Log
import app.grand.tafwak.domain.home.models.HomeStudentData
import app.grand.tafwak.domain.home.repository.HomeRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository
) {

  fun homeService(): Flow<Resource<BaseResponse<HomeStudentData>>> = flow {
    Log.e("invoke", "invoke: starting")
    val result = homeRepository.studentHome()
    emit(result)
  }.flowOn(Dispatchers.IO)

}
