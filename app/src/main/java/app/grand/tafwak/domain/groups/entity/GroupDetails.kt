package app.grand.tafwak.domain.groups.entity


import app.grand.tafwak.domain.auth.entity.model.UserResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import app.grand.tafwak.domain.home.models.Sections

data class GroupDetails(
  @SerializedName("allowed_gender")
  @Expose
  val allowedGender: String = "",
  @SerializedName("class_type")
  @Expose
  val classType: String = "",
  @SerializedName("description")
  @Expose
  val description: String = "",
  @SerializedName("id")
  @Expose
  val id: Int = 0,
  @SerializedName("instructor")
  @Expose
  val sections: Sections = Sections(),
  @SerializedName("name")
  @Expose
  val name: String = "",
  @SerializedName("total")
  @Expose
  val total: Float = 0.0F,
  @SerializedName("app_percentage")
  @Expose
  val app_percentage: Float = 0.0F,
  @SerializedName("profit")
  @Expose
  val profit: Float = 0.0F,
  @SerializedName("public_class_cost")
  @Expose
  val publicClassCost: Float = 0.0F,

  @SerializedName("students")
  @Expose
  val students: ArrayList<UserResponse> = ArrayList(),

  @SerializedName("scheduled")
  @Expose
  val scheduled: List<Scheduled> = listOf(),
  var isTeacher: Boolean = false
)