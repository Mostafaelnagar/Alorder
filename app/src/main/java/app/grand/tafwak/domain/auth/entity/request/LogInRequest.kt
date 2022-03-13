package app.grand.tafwak.domain.auth.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class LogInRequest {
  var email: String = ""
    set(value) {
      validation.emailError.set(null)
      field = value
    }
  var password: String = ""
  var device_token: String = ""
  var validation: LogInValidationException = LogInValidationException()
}

@Keep
class LogInValidationException {
  var emailError: ObservableField<String> = ObservableField<String>()
  var passwordError: ObservableField<String> = ObservableField<String>()

}
