package app.grand.tafwak.data.teacher_group.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import app.grand.tafwak.domain.teacher_groups.entity.request.AddGroupRequest
import javax.inject.Inject

class TeacherGroupsRemoteDataSource @Inject constructor(private val apiService: TeacherGroupsServices) :
  BaseRemoteDataSource() {

  suspend fun teacherGroups(current: Int, status: Int) = safeApiCall {
    apiService.teacherGroups(current, status)
  }

  suspend fun addGroup(addGroupRequest: AddGroupRequest) = safeApiCall {
    apiService.addGroup(addGroupRequest)
  }

}