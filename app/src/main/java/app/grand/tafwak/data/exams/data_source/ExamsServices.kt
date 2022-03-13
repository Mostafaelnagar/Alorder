package app.grand.tafwak.data.exams.data_source

import app.grand.tafwak.domain.exams.entity.ChapterResponse
import app.grand.tafwak.domain.exams.entity.exam_levels.ExamLevelsResponse
import app.grand.tafwak.domain.exams.entity.my_exams.MyExamData
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface ExamsServices {

  @GET("v1/chapters")
  suspend fun getChapters(@Query("subject_id") subjectId: Int): BaseResponse<List<ChapterResponse>>

  @GET("v1/levels")
  suspend fun getExamsLevels(): BaseResponse<List<ExamLevelsResponse>>

  @GET("v1/exams")
  suspend fun getMyExams(@Query("subject_id") subjectId: Int): BaseResponse<List<MyExamData>>

}