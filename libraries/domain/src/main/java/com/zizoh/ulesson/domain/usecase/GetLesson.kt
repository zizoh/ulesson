package com.zizoh.ulesson.domain.usecase

import com.zizoh.ulesson.domain.executor.PostExecutionThread
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.repository.SubjectRepository
import com.zizoh.ulesson.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class GetLesson @Inject constructor(
    private val repository: SubjectRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Int?, Lesson>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Int?): Flow<Lesson> {
        checkNotNull(params)
        return repository.getLesson(params)
    }
}