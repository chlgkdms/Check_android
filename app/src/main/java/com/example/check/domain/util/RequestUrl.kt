package com.example.check.domain.util

sealed interface RequestUrl {
    data object User : RequestUrl {
        const val auth = "/user"
        const val signIn = "$auth/login"
        const val signUp = "$auth/signup"
    }

    data object Subject : RequestUrl {
        const val subject = "/subjects"
        const val fetchSubject = "$subject/all"
        const val fetchSubjectDetail = "$subject/{subject-id}"
    }
}
