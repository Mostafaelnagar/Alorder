package app.grand.tafwak.domain.teacher_groups.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import java.util.ArrayList

@Keep
data class AddGroupRequest(
  var class_type: String = "public",
  var instructor_id: Int = 0,
  var allowed_gender: String? = null,
  var name: String? = null,
  var description: String? = null,
  var schedules: ArrayList<GroupSessionsDates> = ArrayList<GroupSessionsDates>(),
  @Transient
  var validation: AddGroupValidationException = AddGroupValidationException()
)

@Keep
class AddGroupValidationException {
  var nameError: ObservableField<String> = ObservableField<String>()
  var descriptionError: ObservableField<String> = ObservableField<String>()

}