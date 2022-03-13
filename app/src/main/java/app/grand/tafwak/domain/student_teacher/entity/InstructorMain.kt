package app.grand.tafwak.domain.student_teacher.entity

import com.google.gson.annotations.SerializedName
import app.grand.tafwak.domain.general.paginate.Paginate
import app.grand.tafwak.domain.home.models.Sections

data class InstructorMain(
  @SerializedName("data")
  var sections: List<Sections> = listOf()
) : Paginate()
