package com.example.check.data.remote.api

import com.example.check.data.remote.model.subject.request.CreateChecklistRequest
import com.example.check.data.remote.model.subject.request.CreateSubjectRequest
import com.example.check.data.remote.model.subject.response.SubjectDetailResponse
import com.example.check.data.remote.model.subject.response.SubjectResponse
import com.example.check.domain.util.RequestUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SubjectApi {
    @GET(RequestUrl.Subject.fetchSubject)
    suspend fun fetchSubjectList(): SubjectResponse

    @GET(RequestUrl.Subject.fetchSubjectDetail)
    suspend fun fetchSubjectDetail(
        @Path("subject-id") subjectId: Long,
    ): SubjectDetailResponse

    @POST(RequestUrl.Subject.createSubject)
    suspend fun createSubject(
        @Body createSubjectRequest: CreateSubjectRequest,
    )

    @PATCH(RequestUrl.Subject.updateSaved)
    suspend fun updateSaved(
        @Path("checklist-id") checklistId: Long,
    )

    @POST(RequestUrl.Subject.createChecklist)
    suspend fun createChecklist(
        @Path("subject-id") subjectId: Long,
        @Body createChecklistRequest: CreateChecklistRequest,
    )
}