package com.example.check.data.remote.model.subject.response

import com.google.gson.annotations.SerializedName

data class SubjectDetailResponse(
    val id: Long,
    @SerializedName("subject_name") val subjectName: String,
    val checklist: List<CheckList>?,
) {
    data class CheckList(
        @SerializedName("id") val checkListId: Long,
        val nickname: String,
        val title: String,
        val date: String,
        @SerializedName("is_saved") val isSaved: Boolean,
        @SerializedName("check_list_content_list") val checklistContentList: List<ChecklistContentList>,
    ) {
        data class ChecklistContentList(
            val id: Long,
            val content: String,
            @SerializedName("is_cleared") val isCleared: Boolean,
        )
    }
}