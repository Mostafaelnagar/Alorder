package app.grand.tafwak.data.exams.repository

import app.grand.tafwak.data.exams.data_source.ExamsRemoteDataSource
import app.grand.tafwak.domain.exams.entity.ChapterResponse
import app.grand.tafwak.domain.exams.entity.exam_levels.ExamLevelsResponse
import app.grand.tafwak.domain.exams.entity.my_exams.MyExamData
import app.grand.tafwak.domain.exams.repository.ExamsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class ExamsRepositoryImpl @Inject constructor(
  private val remoteDataSource: ExamsRemoteDataSource
) : ExamsRepository {
  override suspend fun getChapters(subjectId: Int): Resource<BaseResponse<List<ChapterResponse>>> =
    remoteDataSource.getChapters(subjectId)

  override suspend fun getExamLevels(): Resource<BaseResponse<List<ExamLevelsResponse>>> =
    remoteDataSource.getExamsLevels()

  override suspend fun getMyExams(subjectId: Int): Resource<BaseResponse<List<MyExamData>>> =
    remoteDataSource.getMyExams(subjectId)


}