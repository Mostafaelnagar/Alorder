package app.grand.tafwak.data.profile.data_source

import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.AvatarResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

  @POST("v1/profile")
  suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<UserResponse>

  @GET("v1/avatars")
  suspend fun avatarList(): BaseResponse<ArrayList<AvatarResponse>>

}