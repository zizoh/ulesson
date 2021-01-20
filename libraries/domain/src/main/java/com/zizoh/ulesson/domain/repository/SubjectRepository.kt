package com.zizoh.ulesson.domain.repository

import com.zizoh.ulesson.domain.models.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by zizoh on 15/January/2021.
 */

interface SubjectRepository {

    fun getSubjects(): Flow<SubjectResult>

    fun getChaptersWithSubjectId(subjectId: Int): Flow<List<Chapter>>

    suspend fun saveWatchedTopic(lesson: Lesson)

    fun getLesson(lessonId: Int): Flow<Lesson>

    fun getMostRecentWatchedTopics(): Flow<List<WatchedTopic>>

    fun getAllWatchedTopics(): Flow<List<WatchedTopic>>
}