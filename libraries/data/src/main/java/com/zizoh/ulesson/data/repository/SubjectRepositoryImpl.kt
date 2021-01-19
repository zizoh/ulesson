package com.zizoh.ulesson.data.repository

import com.zizoh.ulesson.data.contract.cache.ChapterCache
import com.zizoh.ulesson.data.contract.cache.LessonCache
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.mappers.ChapterEntityMapper
import com.zizoh.ulesson.data.mappers.LessonEntityToWatchedTopicMapper
import com.zizoh.ulesson.data.mappers.SubjectEntityMapper
import com.zizoh.ulesson.data.mappers.WatchedTopicEntityMapper
import com.zizoh.ulesson.data.models.ChapterEntity
import com.zizoh.ulesson.data.models.ChapterWithLessonsEntity
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import com.zizoh.ulesson.domain.models.Chapter
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
    private val subjectCache: SubjectCache,
    private val chapterMapper: ChapterEntityMapper
) : SubjectRepository {

    override fun getSubjects(): Flow<List<Subject>> {
        return flow {
            val subjects: List<SubjectEntity> = subjectRemote.getSubjects()
            if (subjects.isNotEmpty()) {
                subjectCache.saveSubjects(subjects)
                subjects.forEach { subject ->
                    val chapters: List<ChapterEntity> = subject.chapters.onEach { chapter ->
                        chapter.subjectId = subject.id
                        chapter.subjectName = subject.name
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

    override fun getChaptersWithSubjectId(subjectId: Int): Flow<List<Chapter>> {
        return flow {
            val chapters: List<ChapterWithLessonsEntity> =
                chapterCache.getChaptersWithLessonsWithSubjectId(subjectId)
            emit(chapterMapper.mapFromEntityList(chapters))
        }
    }

    override suspend fun saveWatchedTopic(lesson: Lesson) {
        val watchedTopic: WatchedTopicEntity = lessonMapper.mapToWatchedTopic(lesson)
        watchedTopicCache.saveWatchedTopic(watchedTopic)
    }

    override fun getMostRecentWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> =
                watchedTopicCache.getMostRecentWatchedTopics()
            val topics = listOf(
                WatchedTopicEntity(
                    1, "Animals And Plants",
                    "https://ulesson-staging.s3.eu-west-2.amazonaws.com/lesson_icons/icons/defaults/thumb/lesson.png",
                    12, 20L,
                    "Biology"
                )
            )
            emit(watchedTopicEntityMapper.mapFromEntityList(topics))
        }
    }

    override fun getAllWatchedTopics(): Flow<List<WatchedTopic>> {
        return flow {
            val watchedTopics: List<WatchedTopicEntity> = watchedTopicCache.getAllWatchedTopics()
            emit(watchedTopicEntityMapper.mapFromEntityList(watchedTopics))
        }
    }
}