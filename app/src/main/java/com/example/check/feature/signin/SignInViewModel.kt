package com.example.check.feature.signin

import androidx.lifecycle.viewModelScope
import com.example.check.base.BaseViewModel
import com.example.check.data.local.storage.AuthDataStorage
import com.example.check.data.remote.api.UserApi
import com.example.check.data.remote.model.user.request.SignInRequest
import com.example.check.data.remote.model.user.response.TokenResponse
import com.example.check.data.util.RequestHandler
import com.example.check.domain.util.NotFoundException
import com.example.check.domain.util.UnAuthorizedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val userApi: UserApi,
    private val authDataStorage: AuthDataStorage,
): BaseViewModel<Unit, SignInSideEffect>(Unit) {

    fun signIn(
        accountId: String,
        password: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<TokenResponse>().request {
                    userApi.signIn(
                        signInRequest = SignInRequest(
                            accountId = accountId,
                            password = password,
                        )
                    )
                }
            }.onSuccess {
                authDataStorage.setAccessToken(it.accessToken)
                postSideEffect(SignInSideEffect.Success)
            }.onFailure {
                postSideEffect(
                    SignInSideEffect.Failure(
                        notFoundUser = it is NotFoundException,
                        invalidPassword = it is UnAuthorizedException,
                        message = it.message,
                    )
                )
            }
        }
    }


}

internal sealed interface SignInSideEffect {
    data object Success : SignInSideEffect
    data class Failure(
        val notFoundUser: Boolean,
        val invalidPassword: Boolean,
        val message: String?,
    ) : SignInSideEffect
}
