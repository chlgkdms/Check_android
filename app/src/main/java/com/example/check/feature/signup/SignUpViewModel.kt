package com.example.check.feature.signup

import androidx.lifecycle.viewModelScope
import com.example.check.base.BaseViewModel
import com.example.check.data.remote.api.UserApi
import com.example.check.data.remote.model.user.request.SignUpRequest
import com.example.check.data.util.RequestHandler
import com.example.check.domain.util.ConflictException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val userApi: UserApi,
) : BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState.getInitialState()) {

    fun signUp(
        accountId: String,
        password: String,
        nickname: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            with(state.value) {
                runCatching {
                    RequestHandler<Unit>().request {
                        userApi.signUp(
                            signUpRequest = SignUpRequest(
                                accountId = accountId,
                                password = password,
                                nickname = nickname,
                            )
                        )
                    }
                }.onSuccess {
                    postSideEffect(SignUpSideEffect.Success("성공적으로 회원가입 되었습니다!"))
                }.onFailure {
                    when (it) {
                        is KotlinNullPointerException -> {
                            postSideEffect(SignUpSideEffect.Success("성공적으로 회원가입 되었습니다!"))
                        }

                        is ConflictException -> {
                            postSideEffect(SignUpSideEffect.Failure("이미 존재하는 이메일이에요"))
                        }
                    }
                }
            }
        }
    }
}

internal data class SignUpState(
    val accountId: String,
    val password: String,
    val nickname: String,
    val repeatPassword: String,
    val emailError: Boolean,
    val passwordError: Boolean,
    val repeatPasswordError: Boolean,
) {
    companion object {
        fun getInitialState() = SignUpState(
            accountId = "",
            password = "",
            nickname = "",
            repeatPassword = "",
            emailError = false,
            passwordError = false,
            repeatPasswordError = false,
        )
    }
}

internal sealed interface SignUpSideEffect {
    data class Success(val message: String) : SignUpSideEffect
    data class Failure(val message: String) : SignUpSideEffect
}
