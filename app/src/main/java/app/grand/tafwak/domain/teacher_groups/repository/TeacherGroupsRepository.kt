package app.grand.tafwak.domain.teacher_groups.repository
import app.grand.tafwak.domain.teacher_groups.entity.TeacherGroupPaginate
import app.grand.tafwak.domain.teacher_groups.entity.request.AddGroupRequest
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface TeacherGroupsRepository {
  suspend fun teacherGroups(current: Int, status: Int): Resource<BaseResponse<TeacherGroupPaginate>>
  suspend fun addGroup(addGroupRequest: AddGroupRequest): Resource<BaseResponse<*>>

}