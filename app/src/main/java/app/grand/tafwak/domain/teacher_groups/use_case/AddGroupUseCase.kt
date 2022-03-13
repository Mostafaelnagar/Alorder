package app.grand.tafwak.domain.teacher_groups.use_case

import app.grand.tafwak.domain.teacher_groups.entity.request.AddGroupRequest
import app.grand.tafwak.domain.teacher_groups.repository.TeacherGroupsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(
  private val teacherGroupsRepository: TeacherGroupsRepository
) {
  operator fun invoke(addGroupRequest: AddGroupRequest): Flow<Resource<BaseResponse<*>>> =
    flow {
      if ((checkValidation(addGroupRequest) || addGroupRequest.instructor_id != 0) && addGroupRequest.schedules.size > 0) {
        emit(Resource.Loading)
        val result = teacherGroupsRepository.addGroup(addGroupRequest)
        emit(result)
      }
    }.flowOn(Dispatchers.IO)

}

private fun checkValidation(addGroupRequest: AddGroupRequest): Boolean {
  var isValid = true
  if (addGroupRequest.name != null) {
    addGroupRequest.validation.nameError.set(Constants.EMPTY)
    isValid = false
  }
  if (addGroupRequest.description != null) {
    addGroupRequest.validation.descriptionError.set(Constants.EMPTY)
    isValid = false
  }

  return isValid
}