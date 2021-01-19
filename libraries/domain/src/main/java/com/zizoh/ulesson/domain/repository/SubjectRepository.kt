package com.zizoh.ulesson.domain.repository

import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.domain.models.WatchedTopic
import kotlinx.coroutines.flow.Flow

/**
 * Created by zizoh on 15/January/2021.
 */

interface SubjectRepository {

    fun getSubjects(): Flow<List<Subject>>

    suspend fun saveWatchedTopic(lesson: Lesson)

    fun getMostRecentWatchedTopics(): Flow<List<WatchedTopic>>

    fun getAllWatchedTopics(): Flow<List<WatchedTopic>>
}