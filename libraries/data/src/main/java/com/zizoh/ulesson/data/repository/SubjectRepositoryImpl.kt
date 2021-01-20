package com.zizoh.ulesson.data.repository

import com.zizoh.ulesson.data.contract.cache.ChapterCache
import com.zizoh.ulesson.data.contract.cache.LessonCache
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.data.mappers.*
import com.zizoh.ulesson.data.models.*
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
    private val lessonEntityToWatchedTopicMapper: LessonEntityToWatchedTopicMapper,
    private val watchedTopicEntityMapper: WatchedTopicEntityMapper,
    private val chapterCache: ChapterCache,
    private val lessonCache: LessonCache,
    private val subjectCache: SubjectCache,
    private val chapterMapper: ChapterEntityMapper,
    private val lessonMapper: LessonEntityMapper
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
                    chapters.forEach { chapter ->
                        val lessons = chapter.lessons.onEach { lesson ->
                            lesson.chapterName = chapter.name
                        }
                        lessonCache.saveLessons(lessons)
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
        val watchedTopic: WatchedTopicEntity =
            lessonEntityToWatchedTopicMapper.mapToWatchedTopic(lesson)
        watchedTopicCache.saveWatchedTopic(watchedTopic)
    }

    override fun getLesson(lessonId: Int): Flow<Lesson> {
        return flow {
            val lesson: LessonEntity = lessonCache.getLesson(lessonId)
            emit(lessonMapper.mapFromEntity(lesson))
        }
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