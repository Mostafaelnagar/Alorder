package app.grand.tafwak.data.groupDetails.data_source

import app.grand.tafwak.domain.groups.entity.GroupDetails
import app.grand.tafwak.domain.groups.entity.JoinToGroupRequest
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupDetailsServices {
  @GET("v1/classes/{group_id}")
  suspend fun groupDetails(@Path("group_id") groupId: Int): BaseResponse<GroupDetails>

  @POST("v1/student/join_request")
  suspend fun sendJoinRequestToGroup(@Body joinToGroupRequest: JoinToGroupRequest): BaseResponse<*>

}