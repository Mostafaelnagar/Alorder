package app.grand.tafwak.domain.exams.repository

import app.grand.tafwak.domain.exams.entity.ChapterResponse
import app.grand.tafwak.domain.exams.entity.exam_levels.ExamLevelsResponse
import app.grand.tafwak.domain.exams.entity.my_exams.MyExamData
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface ExamsRepository {
  suspend fun getChapters(subjectId: Int): Resource<BaseResponse<List<ChapterResponse>>>
  suspend fun getExamLevels(): Resource<BaseResponse<List<ExamLevelsResponse>>>
  suspend fun getMyExams(subjectId: Int): Resource<BaseResponse<List<MyExamData>>>
}