package com.example.check.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.check.base.BaseViewModel
import com.example.check.data.remote.api.SubjectApi
import com.example.check.data.remote.api.UserApi
import com.example.check.data.remote.model.subject.request.CreateChecklistRequest
import com.example.check.data.remote.model.subject.response.SubjectDetailResponse
import com.example.check.data.remote.model.subject.response.SubjectResponse
import com.example.check.data.remote.model.user.response.ProfileResponse
import com.example.check.data.util.RequestHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val subjectApi: SubjectApi,
    private val userApi: UserApi,
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    internal val subjects = mutableStateListOf<SubjectResponse.SubjectList>()
    internal val checklists = mutableStateListOf<SubjectDetailResponse.CheckList>()

    init {
        fetchSubject()
        fetchProfile()
    }

    fun setId(id: Long) = setState {
        state.value.copy(subjectId = id)
    }

    fun checklistId(id: Long) = setState {
        state.value.copy(
            checklist = state.value.checklist.copy(
                checkListId = id
            )
        )
    }

    internal fun fetchSubject() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                subjectApi.fetchSubjectList()
            }.onSuccess {
                subjects.clear()
                subjects.addAll(it.subjectList)
            }
        }
    }

    internal fun fetchSubjectDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<SubjectDetailResponse>().request {
                    subjectApi.fetchSubjectDetail(subjectId = state.value.subjectId)
                }
            }.onSuccess { response ->
                setState {
                    state.value.copy(
                        details = response,
                        checklist = response.checklist?.firstOrNull()
                            ?: SubjectDetailResponse.CheckList(
                                checkListId = 0L,
                                nickname = "",
                                title = "",
                                date = "",
                                isSaved = false,
                                checklistContentList = emptyList(),
                            )
                    )
                }
                /*checklist.clear()
                if (it.checklist != null) {
                    checklist.addAll(it.checklist)
                }*/
            }
        }
    }

    internal fun updateSaved() {
        setState {
            state.value.copy(
                checklist = state.value.checklist.copy(
                    isSaved = !state.value.checklist.isSaved
                )
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<Unit>().request {
                    subjectApi.updateSaved(checklistId = state.value.checklist.checkListId)
                }
            }
        }
    }

    internal fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<ProfileResponse>().request {
                    userApi.fetchProfile()
                }
            }.onSuccess { response ->
                setState {
                    state.value.copy(
                        name = response.nickname,
                        profileChecklist = response.profileChecklist ?: emptyList(),
                    )
                }
            }
        }
    }

    internal fun createChecklist(title: String, content: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<Unit>().request {
                    subjectApi.createChecklist(
                        subjectId = state.value.subjectId,
                        createChecklistRequest = CreateChecklistRequest(
                            title = title,
                            content = content,
                        )
                    )
                }
            }.onSuccess {
                postSideEffect(HomeSideEffect.Success)
            }.onFailure {
                when (it) {
                    is KotlinNullPointerException -> {
                        postSideEffect(HomeSideEffect.Success)
                    }

                    else -> {
                        postSideEffect(HomeSideEffect.Failure)
                    }
                }
            }
        }
    }
}

internal data class HomeState(
    val keyword: String = "",
    val title: String = "",
    val content: String = "",
    val subjectId: Long = 0L,
    val name: String = "",
    val profileChecklist: List<ProfileResponse.ProfileChecklist>? = emptyList(),
    val details: SubjectDetailResponse = SubjectDetailResponse(
        id = 0L,
        subjectName = "",
        checklist = emptyList(),
    ),
    val checklist: SubjectDetailResponse.CheckList = SubjectDetailResponse.CheckList(
        checkListId = 0L,
        nickname = "",
        title = "",
        date = "",
        isSaved = false,
        checklistContentList = emptyList(),
    ),
)

internal sealed interface HomeSideEffect {
    data object Success : HomeSideEffect
    data object Failure : HomeSideEffect
}
