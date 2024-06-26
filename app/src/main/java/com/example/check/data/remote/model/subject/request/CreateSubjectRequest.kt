package com.example.check.data.remote.model.subject.request

import com.google.gson.annotations.SerializedName

data class CreateSubjectRequest(
    @SerializedName("subject_name") val subjectName: String,
)
