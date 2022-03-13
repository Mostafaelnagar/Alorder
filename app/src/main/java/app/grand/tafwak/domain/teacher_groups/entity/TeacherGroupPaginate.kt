package app.grand.tafwak.domain.teacher_groups.entity


import androidx.annotation.Keep
import app.grand.tafwak.domain.general.paginate.Paginate
import com.google.gson.annotations.SerializedName

@Keep
data class TeacherGroupPaginate(
  @SerializedName("classes")
  val classes: TeacherGroupClasses = TeacherGroupClasses(),
) : Paginate()