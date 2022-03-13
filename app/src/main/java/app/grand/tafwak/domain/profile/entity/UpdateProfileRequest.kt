package app.grand.tafwak.domain.profile.entity

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.grand.tafwak.domain.utils.BaseRequest
import com.google.gson.annotations.Expose

@Keep
class UpdateProfileRequest : BaseRequest() {
  var name: String = ""
    set(value) {
      validation.nameError.set(null)
      field = value
    }
  var nickname: String = ""
    set(value) {
      validation.nickNameError.set(null)
      field = value
    }

  var email: String = ""
    set(value) {
      validation.emailError.set(null)
      field = value
    }
  var phone: String = ""
    set(value) {
      validation.phoneError.set(null)
      field = value
    }

  @Expose
  var avatar_id: String? = null

  @Transient
  var accountType: String = ""

  @Transient
  var teacherImage: String = ""

  @Transient
  var device_token: String = ""

  @Transient
  var validation: UpdateProfileValidationException = UpdateProfileValidationException()

}


@Keep
class UpdateProfileValidationException {
  @Transient
  var emailError: ObservableField<String> = ObservableField<String>()

  @Transient
  var nameError: ObservableField<String> = ObservableField<String>()

  @Transient
  var nickNameError: ObservableField<String> = ObservableField<String>()

  @Transient
  var phoneError: ObservableField<String> = ObservableField<String>()

}