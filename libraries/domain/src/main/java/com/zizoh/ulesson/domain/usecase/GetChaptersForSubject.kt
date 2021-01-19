package com.zizoh.ulesson.domain.usecase

import com.zizoh.ulesson.domain.executor.PostExecutionThread
import com.zizoh.ulesson.domain.models.Chapter
import com.zizoh.ulesson.domain.repository.SubjectRepository
import com.zizoh.ulesson.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class GetChaptersForSubject @Inject constructor(
    private val repository: SubjectRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Int?, List<Chapter>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Int?): Flow<List<Chapter>> {
        checkNotNull(params)
        return repository.getChaptersWithSubjectId(params)
    }
}