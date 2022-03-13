package app.grand.tafwak.domain.utils

import androidx.room.TypeConverter
import app.grand.tafwak.domain.home.models.Companies
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.domain.home.models.Slider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
//    @TypeConverter
//  fun List<Classes>.toJsonString(): String = Gson().toJson(this)
//
//  @TypeConverter
//  fun String.toJsonModel(modelClass: List<Classes>): List<Classes> =
//    Gson().fromJson(this, modelClass)

  @TypeConverter
  fun fromClassesString(value: String): List<Companies> {
    val listType: Type = object : TypeToken<List<Companies>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromClassesList(list: List<Companies>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromInstructorString(value: String): List<Sections> {
    val listType: Type = object : TypeToken<List<Sections>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromInstructorList(list: List<Sections>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromSliderString(value: String): List<Slider> {
    val listType: Type = object : TypeToken<List<Slider>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromSliderList(list: List<Slider>): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}