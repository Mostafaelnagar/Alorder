package app.grand.tafwak.domain.profile.entity

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.grand.tafwak.domain.utils.isValidEmail
import app.grand.tafwak.presentation.base.utils.Constants

@Keep
class UpdatePassword {
  var old_password: String = ""
    set(value) {
      validation.oldPasswordError.set(null)
      field = value
    }
  var new_password: String = ""
    set(value) {
      validation.newPasswordError.set(null)
      field = value
    }
  var new_password_confirmation: String = ""
    set(value) {
      validation.newPasswordConfirmError.set(null)
      field = value
    }
  var validation: UpdateValidationException = UpdateValidationException()
}

@Keep
class UpdateValidationException {
  var oldPasswordError: ObservableField<String> = ObservableField<String>()
  var newPasswordError: ObservableField<String> = ObservableField<String>()
  var newPasswordConfirmError: ObservableField<String> = ObservableField<String>()
  private fun checkUpdatePasswordValidation(request: UpdatePassword): Boolean {
    var isValid = true
    if (request.old_password.isEmpty()) {
      request.validation.oldPasswordError.set(Constants.EMPTY)
      isValid = false
    }
    if (!request.new_password.isValidEmail()) {
      request.validation.newPasswordError.set(Constants.INVALID_EMAIL)
      isValid = false
    }
    if (!request.new_password_confirmation.isValidEmail()) {
      request.validation.newPasswordError.set(Constants.INVALID_EMAIL)
      isValid = false
    }
    return isValid
  }
}