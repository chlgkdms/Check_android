package com.example.check.data.remote.model.user.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val nickname: String,
    @SerializedName("profile_check_list") val profileChecklist: List<ProfileChecklist>?
) {
    data class ProfileChecklist(
        val title: String,
        val id: Long,
        @SerializedName("is_saved") val isSaved: Boolean,
        @SerializedName("check_list_content_list") val content: List<String>,
        val date: String,
        val writer: String,
    )
}