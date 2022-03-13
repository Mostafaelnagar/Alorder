package app.grand.tafwak.domain.home.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class HomeStudentData(
  @SerializedName("classes")
  @Expose
  val aClasses: ArrayList<Companies> = ArrayList(),
  @SerializedName("instructors")
  @Expose
  val sections: ArrayList<Sections> = ArrayList(),
  @SerializedName("notification_count")
  @Expose
  val notificationCount: Int = 0,
  @SerializedName("sliders")
  @Expose
  val sliders: List<Slider> = listOf()
)