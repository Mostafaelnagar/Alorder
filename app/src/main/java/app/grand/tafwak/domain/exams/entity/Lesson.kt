package app.grand.tafwak.domain.exams.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Lesson(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)