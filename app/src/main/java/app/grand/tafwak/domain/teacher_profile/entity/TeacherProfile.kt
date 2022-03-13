package app.grand.tafwak.domain.teacher_profile.entity

import androidx.annotation.Keep
import app.grand.tafwak.domain.home.models.Sections

@Keep
data class TeacherProfile(
  val sections: Sections = Sections()
)