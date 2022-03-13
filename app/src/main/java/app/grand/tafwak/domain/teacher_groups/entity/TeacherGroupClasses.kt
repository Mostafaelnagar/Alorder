package app.grand.tafwak.domain.teacher_groups.entity

import com.google.gson.annotations.SerializedName

data class TeacherGroupClasses(
  @SerializedName("data")
  val data: List<GroupData> = listOf()
)
