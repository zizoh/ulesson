package com.zizoh.ulesson.domain.usecase

import com.zizoh.ulesson.domain.executor.PostExecutionThread
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.repository.SubjectRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by zizoh on 20/January/2021.
 */

class SaveWatchedTopic @Inject constructor(
    private val repository: SubjectRepository,
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke(lesson: Lesson) {
        withContext(postExecutionThread.io) {
            repository.saveWatchedTopic(lesson)
        }
    }
}