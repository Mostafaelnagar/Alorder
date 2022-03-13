package app.grand.tafwak.presentation.auth.schoolInfo.entity

import androidx.annotation.Keep

@Keep
class UpdateEducationRequest(
  var educational_stage_id: Int,
  var grade_id: Int
)
