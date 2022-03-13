package app.grand.tafwak.domain.teacher_home.entity


import androidx.annotation.Keep
import app.grand.tafwak.domain.home.models.Companies
import app.grand.tafwak.domain.home.models.Slider
import com.google.gson.annotations.SerializedName
@Keep
data class TeacherHomeData(
  @SerializedName("classes")
  val aClasses: List<Companies> = listOf(),
  @SerializedName("notification_count")
  val notificationCount: Int = 0,
  @SerializedName("sliders")
  val sliders: List<Slider> = listOf()
)