package app.grand.tafwak.domain.teacher_home.use_case

import android.util.Log
import app.grand.tafwak.domain.teacher_home.entity.TeacherHomeData
import app.grand.tafwak.domain.teacher_home.repository.TeacherHomeRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TeacherHomeUseCase @Inject constructor(
  private val homeRepository: TeacherHomeRepository) {

  fun teacherHomeService(): Flow<Resource<BaseResponse<TeacherHomeData>>> = flow {
    Log.e("invoke", "invoke: starting")
    emit(Resource.Loading)
    val result = homeRepository.teacherHome()

    emit(result)
  }.flowOn(Dispatchers.IO)

}
