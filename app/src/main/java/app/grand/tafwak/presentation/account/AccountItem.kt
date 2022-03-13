package app.grand.tafwak.presentation.account

import android.graphics.drawable.Drawable
import androidx.navigation.NavDirections

data class AccountItem(
  var icon: Drawable,
  var title: String,
  var body: String,
  var action: NavDirections,
)