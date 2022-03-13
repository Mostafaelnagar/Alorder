package app.grand.tafwak.domain.account.repository

import app.grand.tafwak.domain.account.entity.request.SendFirebaseTokenRequest
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest): Resource<BaseResponse<*>>

  suspend fun logOut(): Resource<BaseResponse<*>>

  suspend fun isLoggedIn(isLoggedIn: Boolean)

  suspend fun getIsLoggedIn(): Flow<Boolean>

  suspend fun saveFirebaseTokenToLocal(firebaseToken: String)

  suspend fun getFirebaseTokenToLocal(): Flow<String>

  suspend fun setFirstTime(isFirstTime: Boolean)

  suspend fun isFirstTime(): Flow<Boolean>

  suspend fun saveUserToLocal(user: UserResponse)

  suspend fun getUserToLocal(): Flow<User>

  suspend fun saveUserToken(userToken: String)

  suspend fun getUserToken(): Flow<String>

  suspend fun saveRegisterStep(register_step: String)

  suspend fun getRegisterStep(): Flow<String>

  suspend fun saveCountryId(country_id: String)

  suspend fun getCountryId(): Flow<String>

  suspend fun clearPreferences()

}