package app.grand.tafwak.data.teacher_home.repository

import app.grand.tafwak.data.teacher_home.data_source.TeacherHomeRemoteDataSource
import app.grand.tafwak.domain.teacher_home.entity.TeacherHomeData
import app.grand.tafwak.domain.teacher_home.repository.TeacherHomeRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class TeacherHomeRepositoryImpl @Inject constructor(private val remoteDataSource: TeacherHomeRemoteDataSource) :
  TeacherHomeRepository {
  override suspend fun teacherHome(): Resource<BaseResponse<TeacherHomeData>> =
    remoteDataSource.homeStudent()
}