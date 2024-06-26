package com.example.check.data.remote.model.subject.request

data class CreateChecklistRequest(
    val title: String,
    val content: List<String>,
)