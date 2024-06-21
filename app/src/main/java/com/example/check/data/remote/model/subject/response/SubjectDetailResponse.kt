package com.example.check.data.remote.model.subject.response

import com.google.gson.annotations.SerializedName

data class SubjectDetailResponse(
    val id: Long,
    @SerializedName("subject_name") val subjectName: String,
    val checkList: List<CheckList>,
) {
    data class CheckList(
        @SerializedName("id") val checkListId: Long,
        val writer: String,
        val title: String,
        val date: String,
        @SerializedName("is_saved") val isSaved: Boolean,
    )
}