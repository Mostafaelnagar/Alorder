package app.grand.tafwak.data.exams.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class ExamsRemoteDataSource @Inject constructor(private val apiService: ExamsServices) :
  BaseRemoteDataSource() {

  suspend fun getChapters(subjectId:Int) = safeApiCall {
    apiService.getChapters(subjectId)
  }

  suspend fun getExamsLevels() = safeApiCall {
    apiService.getExamsLevels()
  }

  suspend fun getMyExams(subjectId:Int) = safeApiCall {
    apiService.getMyExams(subjectId)
  }
}