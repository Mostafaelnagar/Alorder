package app.grand.tafwak.domain.exams.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class ChapterResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lessons")
    val lessons: List<Lesson> = listOf(),
    @SerializedName("name")
    val name: String = ""
)