package app.grand.tafwak.domain.home.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import app.grand.tafwak.domain.reviews.entity.Reviews
import com.structure.base_mvvm.R

@Keep
data class Sections(
  @Expose
  val id: Int = 0,
  @SerializedName("name")
  @Expose
  val name: String = "",
  val image: Int = R.drawable.company,

  )