package app.grand.tafwak.domain.profile.entity


import com.google.gson.annotations.SerializedName

data class AvatarResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = ""
)