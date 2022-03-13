package app.grand.tafwak.domain.home.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.structure.base_mvvm.R

data class Companies(
  @SerializedName("id")
  @Expose
  val id: Int = 0,
  @SerializedName("name")
  @Expose
  val name: String = "",
  val image: Int = R.drawable.company,

  )