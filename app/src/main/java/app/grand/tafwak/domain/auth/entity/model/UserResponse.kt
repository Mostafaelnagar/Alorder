package app.grand.tafwak.domain.auth.entity.model

import androidx.annotation.Keep
import app.grand.tafwak.domain.countries.entity.Country
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
  val email: String = "",
  val id: Int = 0,
  val image: String = "",
  @SerializedName("register_steps")
  val registerSteps: Int = 0,
  val nickname: String? = "",
  val name: String = "",
  val phone: String = "",
  @SerializedName("account_type")
  val accountType: String = "",
  @SerializedName("country")
  val country: Country = Country(),
  val token: String = ""
)