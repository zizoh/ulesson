package com.zizoh.ulesson.data.repository

import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.mappers.LessonEntityToWatchedTopicMapper
import com.zizoh.ulesson.data.mappers.SubjectEntityMapper
import com.zizoh.ulesson.data.mappers.WatchedTopicEntityMapper
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import com.zizoh.ulesson.data.utils.DateUtils
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.domain.models.WatchedTopic
import com.zizoh.ulesson.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectRemote: SubjectRemote,
    private val subjectEntityMapper: SubjectEntityMapper,
    private val subjectCache: SubjectCache,
    private val lessonMapper: LessonEntityToWatchedTopicMapper,
    private val watchedTopicEntityMapper: WatchedTopicEntityMapper
) : SubjectRepository {

    override fun getSubjects(): Flow<List<Subject>> {
        return flow {
            val subjects: List<SubjectEntity> = subjectRemote.getSubjects()
            emit(subjectEntityMapper.mapFromEntityList(subjects))
        }
    }

    override suspend fun saveWatchedTopic(lesson: Lesson) {
        val watchedTopic: WatchedTopicEntity = lessonMapper.mapToWatchedTopic(lesson)
            .apply {
                watchedDate = DateUtils.getCurrentTime()
            }
        subjectCache.saveWatchedTopic(watchedTopic)
    }

    override fun getMostRecentWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> = subjectCache.getMostRecentWatchedTopics()
            emit(watchedTopicEntityMapper.mapFromEntityList(watchedTopics))
        }
    }

    override fun getAllWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> = subjectCache.getAllWatchedTopics()
            emit(watchedTopicEntityMapper.mapFromEntityList(watchedTopics))
        }
    }
}