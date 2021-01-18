package com.zizoh.ulesson.domain.usecase

import com.zizoh.ulesson.domain.executor.PostExecutionThread
import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.domain.models.WatchedTopic
import com.zizoh.ulesson.domain.repository.SubjectRepository
import com.zizoh.ulesson.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by zizoh on 17/January/2021.
 */

class GetMostRecentWatchedTopics @Inject constructor(
    private val repository: SubjectRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<WatchedTopic>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Unit?): Flow<List<WatchedTopic>> {
        return repository.getMostRecentWatchedTopics()
    }
}