package com.example.check.domain.util

sealed interface RequestUrl {
    data object User : RequestUrl {
        const val auth = "/user/auth/"
        const val user = "/user/"
    }

}
