package app.grand.tafwak.data.teacher_home.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class TeacherHomeRemoteDataSource @Inject constructor(private val apiService: TeacherHomeServices) :
  BaseRemoteDataSource() {

  suspend fun homeStudent() = safeApiCall {
    apiService.teacherHomeStudent()
  }
}