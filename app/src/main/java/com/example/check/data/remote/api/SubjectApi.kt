package com.example.check.data.remote.api

import com.example.check.data.remote.model.subject.response.SubjectResponse
import com.example.check.domain.util.RequestUrl
import retrofit2.http.GET

interface SubjectApi {
    @GET(RequestUrl.Subject.fetchSubject)
    suspend fun fetchSubjectList(): SubjectResponse

    @GET(RequestUrl.Subject.fetchSubjectDetail)
    suspend fun fetchSubjectDetail()
}