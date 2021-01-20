package com.zizoh.ulesson.domain.usecase

import com.zizoh.ulesson.domain.executor.PostExecutionThread
import com.zizoh.ulesson.domain.models.SubjectResult
import com.zizoh.ulesson.domain.repository.SubjectRepository
import com.zizoh.ulesson.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class GetSubjects @Inject constructor(
    private val repository: SubjectRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, SubjectResult>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Unit?): Flow<SubjectResult> {
        return repository.getSubjects()
    }
}