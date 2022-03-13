package app.grand.tafwak.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.auth.entity.model.UserSerializer
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

  private val STORE_NAME = "app_data_store"
  private val STORE_NAME_FIRST_TIME = "app_data_store_first_time"
  private val USER_DATA_STORE_FILE_NAME = "user_store.pb"
  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)
  private val Context.dataStoreFirstTime: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME_FIRST_TIME)
  private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserSerializer
  )

  suspend fun saveFireBaseToken(token: String) {
    context.dataStore.edit {
      it[FIREBASE_TOKEN] = token
    }
  }

  fun getFireBaseToken() = context.dataStore.data.map {
    it[FIREBASE_TOKEN] ?: ""
  }

  suspend fun isFirstTime(isFirstTime: Boolean) {
    context.dataStoreFirstTime.edit {
      it[FIRST_TIME] = isFirstTime
    }
  }

  fun getIsFirstTime() = context.dataStoreFirstTime.data.map {
    it[FIRST_TIME] ?: true
  }

  suspend fun isLoggedIn(isLoggedIn: Boolean) {
    context.dataStore.edit {
      it[IS_LOGGED_IN] = isLoggedIn
    }
  }

  fun getIsLoggedIn() = context.dataStore.data.map {
    it[IS_LOGGED_IN] ?: false
  }

  suspend fun userToken(userToken: String) {
    context.dataStore.edit {
      it[USER_TOKEN] = userToken
    }
  }

  fun getUserToken() = context.dataStore.data.map {
    it[USER_TOKEN] ?: ""
  }

  suspend fun registerStep(register_step: String) {
    context.dataStore.edit {
      it[REGISTER_STEP] = register_step
    }
  }

  fun getRegisterStep() = context.dataStore.data.map {
    it[REGISTER_STEP] ?: ""
  }

  suspend fun countryId(country_id: String) {
    context.dataStore.edit {
      it[COUNTRY_ID] = country_id
    }
  }

  fun getCountryId() = context.dataStore.data.map {
    it[COUNTRY_ID] ?: "1"
  }

  suspend fun saveUser(user: UserResponse) {
    context.userDataStore.updateData { store ->
      store.toBuilder()
        .setId(user.id)
        .setEmail(user.email)
        .setName(user.name)
        .setImage(user.image ?: "")
        .setRegisterSteps(user.registerSteps)
        .setNickname(user.nickname ?: "")
        .setPhone(user.phone)
        .setAccountType(user.accountType)
        .setCountryId(user.country.id)
        .setToken(user.token)
        .build()

    }
  }

  fun getUser(): Flow<User> = context.userDataStore.data

  //Old Pref
  companion object {
    val FIREBASE_TOKEN = stringPreferencesKey("FIREBASE_TOKEN")
    val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
    val REGISTER_STEP = stringPreferencesKey("REGISTER_STEP")
    val COUNTRY_ID = stringPreferencesKey("COUNTRY_ID")
    val FIRST_TIME = booleanPreferencesKey("FIRST_TIME")
    val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
  }


  suspend fun clearPreferences() {
    context.dataStore.edit {
      it.clear()
    }
    context.userDataStore.updateData {
      it.toBuilder().clear().build()
    }
  }
}