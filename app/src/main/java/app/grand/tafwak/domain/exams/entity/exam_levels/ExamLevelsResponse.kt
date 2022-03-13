package app.grand.tafwak.domain.exams.entity.exam_levels


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class ExamLevelsResponse(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)