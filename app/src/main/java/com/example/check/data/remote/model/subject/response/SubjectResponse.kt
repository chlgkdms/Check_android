package com.example.check.data.remote.model.subject.response

import com.google.gson.annotations.SerializedName

data class SubjectResponse(
    @SerializedName("subject_list") val subjectList: List<SubjectList>
) {
    data class SubjectList(
        val id: Long,
        @SerializedName("subject_name") val subjectName: String,
        val writer: String,
    )
}

