package com.zizoh.ulesson.data.repository

import com.zizoh.ulesson.data.contract.cache.ChapterCache
import com.zizoh.ulesson.data.contract.cache.LessonCache
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.mappers.LessonEntityToWatchedTopicMapper
import com.zizoh.ulesson.data.mappers.SubjectEntityMapper
import com.zizoh.ulesson.data.mappers.WatchedTopicEntityMapper
import com.zizoh.ulesson.data.models.ChapterEntity
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
    private val watchedTopicCache: WatchedTopicCache,
    private val lessonMapper: LessonEntityToWatchedTopicMapper,
    private val watchedTopicEntityMapper: WatchedTopicEntityMapper,
    private val chapterCache: ChapterCache,
    private val lessonCache: LessonCache,
    private val subjectCache: SubjectCache
) : SubjectRepository {

    override fun getSubjects(): Flow<List<Subject>> {
        return flow {
            val subjects: List<SubjectEntity> = subjectRemote.getSubjects()
            if (subjects.isNotEmpty()) {
                subjectCache.saveSubjects(subjects)
                subjects.forEach { subject ->
                    val chapters: List<ChapterEntity> = subject.chapters.onEach { chapter ->
                        chapter.subjectId = subject.id
                    }
                    chapterCache.saveChapters(chapters)
                    chapters.forEach { lessons ->
                        lessonCache.saveLessons(lessons.lessons)
                    }

                }
            }
            val subjectsCache = subjectCache.getSubjects()
            emit(subjectEntityMapper.mapFromEntityList(subjectsCache))
        }
    }

    override suspend fun saveWatchedTopic(lesson: Lesson) {
        val watchedTopic: WatchedTopicEntity = lessonMapper.mapToWatchedTopic(lesson)
            .apply {
                watchedDate = DateUtils.getCurrentTime()
            }
        watchedTopicCache.saveWatchedTopic(watchedTopic)
    }

    override fun getMostRecentWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> =
                watchedTopicCache.getMostRecentWatchedTopics()
            emit(watchedTopicEntityMapper.mapFromEntityList(watchedTopics))
        }
    }

    override fun getAllWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> = watchedTopicCache.getAllWatchedTopics()
            emit(watchedTopicEntityMapper.mapFromEntityList(watchedTopics))
        }
    }
}