package app.grand.tafwak.domain.exams.use_case

import app.grand.tafwak.domain.exams.entity.ChapterResponse
import app.grand.tafwak.domain.exams.entity.exam_levels.ExamLevelsResponse
import app.grand.tafwak.domain.exams.entity.my_exams.MyExamData
import app.grand.tafwak.domain.exams.repository.ExamsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExamsUseCase @Inject constructor(
  private val examsRepository: ExamsRepository
) {
  fun getChapters(subjectId: Int): Flow<Resource<BaseResponse<List<ChapterResponse>>>> =
    flow {
      emit(Resource.Loading)
      val result = examsRepository.getChapters(subjectId)
      emit(result)
    }.flowOn(Dispatchers.IO)

  fun getMyExams(subjectId: Int): Flow<Resource<BaseResponse<List<MyExamData>>>> =
    flow {
      emit(Resource.Loading)
      val result = examsRepository.getMyExams(subjectId)
      emit(result)
    }.flowOn(Dispatchers.IO)

  fun getExamLevels(): Flow<Resource<BaseResponse<List<ExamLevelsResponse>>>> =
    flow {
      emit(Resource.Loading)
      val result = examsRepository.getExamLevels()
      emit(result)
    }.flowOn(Dispatchers.IO)

}