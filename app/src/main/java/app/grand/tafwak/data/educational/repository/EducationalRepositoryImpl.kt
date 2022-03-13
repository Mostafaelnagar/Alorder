package app.grand.tafwak.data.educational.repository

import app.grand.tafwak.data.educational.data_source.EducationalRemoteDataSource
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.educational.entity.model.Grade
import app.grand.tafwak.domain.educational.entity.model.Stage
import app.grand.tafwak.domain.educational.entity.request.RegisterStep3
import app.grand.tafwak.domain.educational.entity.request.RegisterStep4
import app.grand.tafwak.domain.educational.repository.EducationalRepository
import app.grand.tafwak.domain.home.models.Subject
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.auth.schoolInfo.entity.UpdateEducationRequest
import javax.inject.Inject

class EducationalRepositoryImpl @Inject constructor(
  private val remoteDataSource: EducationalRemoteDataSource
) : EducationalRepository {
  override suspend fun educationalStages(): Resource<BaseResponse<List<Stage>>> =
    remoteDataSource.educationalStages()

  override suspend fun educationalStageGrades(stage_id: Int): Resource<BaseResponse<List<Grade>>> =
    remoteDataSource.educationalStageGrades(stage_id)

  override suspend fun registerStep3(registerStep3: RegisterStep3): Resource<BaseResponse<*>> =
    remoteDataSource.registerStep3(registerStep3)

  override suspend fun registerStep4(registerStep4: RegisterStep4): Resource<BaseResponse<UserResponse>> =
    remoteDataSource.registerStep4(registerStep4)

  override suspend fun updateEducation(updateEducationRequest: UpdateEducationRequest): Resource<BaseResponse<*>> =
    remoteDataSource.updateEducation(updateEducationRequest)

  override suspend fun testSubjects(educational_stage_id: Int): Resource<BaseResponse<List<Subject>>> =
    remoteDataSource.testSubjects(educational_stage_id)

}