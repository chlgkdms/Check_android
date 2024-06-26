package com.example.check.feature.createsubject

import androidx.lifecycle.viewModelScope
import com.example.check.base.BaseViewModel
import com.example.check.data.remote.api.SubjectApi
import com.example.check.data.remote.model.subject.request.CreateSubjectRequest
import com.example.check.data.util.RequestHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateSubjectViewModel @Inject constructor(
    private val subjectApi: SubjectApi,
) : BaseViewModel<CreateSubjectState, CreateSubjectSideEffect>(CreateSubjectState()) {

    internal fun createSubject() {
        viewModelScope.launch(Dispatchers.IO) {
            with(state.value) {
                runCatching {
                    RequestHandler<Unit>().request {
                        subjectApi.createSubject(
                            createSubjectRequest = CreateSubjectRequest(
                                subjectName = subjectName,
                            )
                        )
                    }
                }.onSuccess {
                    postSideEffect(CreateSubjectSideEffect.Success)
                }.onFailure {
                    when (it) {
                        is KotlinNullPointerException -> {
                            postSideEffect(CreateSubjectSideEffect.Success)
                        }

                        else -> {
                            postSideEffect(CreateSubjectSideEffect.Failure)

                        }
                    }
                }
            }
        }
    }

    internal fun setSubjectName(subjectName: String) = setState {
        state.value.copy(subjectName = subjectName)
    }

}

internal data class CreateSubjectState(
    val subjectName: String = "",
)

internal sealed interface CreateSubjectSideEffect {
    data object Success : CreateSubjectSideEffect
    data object Failure : CreateSubjectSideEffect
}
