package app.grand.tafwak.domain.teacher_groups.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class GroupData(
    @SerializedName("allowed_gender")
    val allowedGender: String = "",
    @SerializedName("class_type")
    val classType: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("students_count")
    val studentsCount: Int = 0
)