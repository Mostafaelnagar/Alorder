package app.grand.tafwak.domain.exams.entity.my_exams


import com.google.gson.annotations.SerializedName

data class MyExamData(
    @SerializedName("count_chapters")
    val countChapters: Int = 0,
    @SerializedName("count_correct_answers")
    val countCorrectAnswers: Int = 0,
    @SerializedName("count_lessons")
    val countLessons: Int = 0,
    @SerializedName("count_questions")
    val countQuestions: Int = 0,
    @SerializedName("date")
    val date: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("question_levels")
    val questionLevels: String = ""
)