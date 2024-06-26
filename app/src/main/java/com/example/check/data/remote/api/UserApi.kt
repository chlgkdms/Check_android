package com.example.check.data.remote.api

import com.example.check.data.remote.model.user.request.SignInRequest
import com.example.check.data.remote.model.user.request.SignUpRequest
import com.example.check.data.remote.model.user.response.ProfileResponse
import com.example.check.data.remote.model.user.response.TokenResponse
import com.example.check.domain.util.RequestUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST(RequestUrl.User.signIn)
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): TokenResponse

    @POST(RequestUrl.User.signUp)
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    )

    @GET(RequestUrl.User.profile)
    suspend fun fetchProfile(): ProfileResponse
}