package app.grand.tafwak.data.teacher_profile.repository

import app.grand.tafwak.data.teacher_profile.data_source.TeacherProfileRemoteDataSource
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.domain.teacher_profile.repository.TeacherProfileRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class TeacherProfileRepositoryImpl @Inject constructor(private val remoteDataSource: TeacherProfileRemoteDataSource) :
  TeacherProfileRepository {
  override suspend fun teacherProfile(instructor_id: Int): Resource<BaseResponse<Sections>> =
    remoteDataSource.teacherProfile(instructor_id)

}