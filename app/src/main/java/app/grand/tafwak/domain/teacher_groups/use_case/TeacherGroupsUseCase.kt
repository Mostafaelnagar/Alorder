package app.grand.tafwak.domain.teacher_groups.use_case

import app.grand.tafwak.domain.teacher_groups.entity.TeacherGroupPaginate
import app.grand.tafwak.domain.teacher_groups.repository.TeacherGroupsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TeacherGroupsUseCase @Inject constructor(
  private val homeRepository: TeacherGroupsRepository,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
  operator fun invoke(
    current: Int,
    status: Int
  ): Flow<Resource<BaseResponse<TeacherGroupPaginate>>> =
    flow {
      emit(Resource.Loading)
      val result = homeRepository.teacherGroups(current, status)
      emit(result)
    }.flowOn(dispatcher)

}
