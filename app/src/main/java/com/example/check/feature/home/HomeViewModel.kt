package com.example.check.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.check.base.BaseViewModel
import com.example.check.data.remote.api.SubjectApi
import com.example.check.data.remote.model.subject.response.SubjectResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val subjectApi: SubjectApi,
) :BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    internal val subjects = mutableStateListOf<SubjectResponse.SubjectList>()

    init {
        fetchSubject()
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
            run {

            }
        }
    }
}

internal data class HomeState(
    val keyword: String = "",
    val title: String = "",
    val content: String = "",
)

internal sealed interface HomeSideEffect {
    data object Success : HomeSideEffect
}
