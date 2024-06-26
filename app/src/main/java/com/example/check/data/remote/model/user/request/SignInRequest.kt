package com.example.check.data.remote.model.user.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String,
)