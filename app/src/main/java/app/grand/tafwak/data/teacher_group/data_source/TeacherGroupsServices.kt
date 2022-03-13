package app.grand.tafwak.data.teacher_group.data_source

import app.grand.tafwak.domain.teacher_groups.entity.TeacherGroupPaginate
import app.grand.tafwak.domain.teacher_groups.entity.request.AddGroupRequest
import app.grand.tafwak.domain.teacher_home.entity.TeacherHomeData
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TeacherGroupsServices {
  @GET("v1/classes")
  suspend fun teacherGroups(
    @Query("current") current: Int,
    @Query("status") status: Int
  ): BaseResponse<TeacherGroupPaginate>

  @POST("v1/classes")
  suspend fun addGroup(@Body addGroupRequest: AddGroupRequest): BaseResponse<*>

}