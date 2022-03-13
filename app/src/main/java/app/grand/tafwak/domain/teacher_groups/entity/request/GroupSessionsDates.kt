package app.grand.tafwak.domain.teacher_groups.entity.request

data class GroupSessionsDates(
  var day: String = "",
  var month: String = "",
  var year: String = "",
  var start_time: String = "",
  var from_type: String = "ص",
  var end_time: String = "",
  var to_type: String = "م",
  var scheduled_date: String = "",
  var id: Int = 1,
) {
  fun isValid(): Boolean {
    return (day.isNotEmpty() && month.isNotEmpty()
      && year.isNotEmpty()
      && start_time.isNotEmpty()
      && end_time.isNotEmpty()
      )
  }
}
