package app.grand.tafwak.data.teacher_group.repository

import app.grand.tafwak.data.teacher_group.data_source.TeacherGroupsRemoteDataSource
import app.grand.tafwak.domain.teacher_groups.entity.TeacherGroupPaginate
import app.grand.tafwak.domain.teacher_groups.entity.request.AddGroupRequest
import app.grand.tafwak.domain.teacher_groups.repository.TeacherGroupsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class TeacherGroupsRepositoryImpl @Inject constructor(private val remoteDataSource: TeacherGroupsRemoteDataSource) :
  TeacherGroupsRepository {
  override suspend fun teacherGroups(current: Int, status: Int): Resource<BaseResponse<TeacherGroupPaginate>> =
    remoteDataSource.teacherGroups(current, status)

  override suspend fun addGroup(addGroupRequest: AddGroupRequest): Resource<BaseResponse<*>> =
    remoteDataSource.addGroup(addGroupRequest)
}