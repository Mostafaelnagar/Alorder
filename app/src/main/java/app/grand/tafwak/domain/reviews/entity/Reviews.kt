package app.grand.tafwak.domain.reviews.entity

import androidx.annotation.Keep
import app.grand.tafwak.domain.home.models.Sections

@Keep
data class Reviews(
    var user: Sections = Sections(),
    var rate: String = "0",
    var review: String = ""
)
