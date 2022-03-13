package app.grand.tafwak.data.teacher_home.data_source

import app.grand.tafwak.domain.teacher_home.entity.TeacherHomeData
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.GET

interface TeacherHomeServices {
  @GET("v1/instructor/home")
  suspend fun teacherHomeStudent(): BaseResponse<TeacherHomeData>

}