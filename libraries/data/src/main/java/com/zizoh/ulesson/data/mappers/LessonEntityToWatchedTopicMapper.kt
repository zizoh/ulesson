package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.models.WatchedTopicEntity
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.models.WatchedTopic
import javax.inject.Inject

class LessonEntityToWatchedTopicMapper @Inject constructor(){

    fun mapToWatchedTopic(lesson: Lesson): WatchedTopicEntity {
        return with(lesson) {
            WatchedTopicEntity(id, name, icon, mediaUrl, subjectId, subjectName, chapterId)
        }
    }

}
