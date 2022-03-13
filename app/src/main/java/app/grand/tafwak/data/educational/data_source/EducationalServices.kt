package app.grand.tafwak.data.educational.data_source

import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.educational.entity.model.Grade
import app.grand.tafwak.domain.educational.entity.model.Stage
import app.grand.tafwak.domain.educational.entity.request.RegisterStep3
import app.grand.tafwak.domain.educational.entity.request.RegisterStep4
import app.grand.tafwak.domain.home.models.Subject
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.presentation.auth.schoolInfo.entity.UpdateEducationRequest
import retrofit2.http.*

interface EducationalServices {

  @GET("v1/educational_stages")
  suspend fun educationalStages(): BaseResponse<List<Stage>>

  @GET("v1/grades")
  suspend fun educationalStageGrade(@Query("educational_stage_id") stage_id: Int): BaseResponse<List<Grade>>

  @POST("v1/auth/register")
  suspend fun registerStep3(@Body request: RegisterStep3): BaseResponse<*>

  @POST("v1/auth/register")
  suspend fun registerStep4(@Body request: RegisterStep4): BaseResponse<UserResponse>

  @POST("v1/profile/educations")
  suspend fun updateEducation(@Body updateEducationRequest: UpdateEducationRequest): BaseResponse<*>

  @GET("v1/subjects")
  suspend fun testSubjects(@Query("educational_stage_id") educational_stage_id: Int): BaseResponse<List<Subject>>

}