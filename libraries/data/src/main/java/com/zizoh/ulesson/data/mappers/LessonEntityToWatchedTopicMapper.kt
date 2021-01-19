package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.models.WatchedTopicEntity
import com.zizoh.ulesson.data.utils.DateUtils
import com.zizoh.ulesson.domain.models.Lesson
import javax.inject.Inject

class LessonEntityToWatchedTopicMapper @Inject constructor() {

    fun mapToWatchedTopic(lesson: Lesson): WatchedTopicEntity {
        return with(lesson) {
            WatchedTopicEntity(id, name, icon, subjectId, DateUtils.getCurrentTime(), "")
        }
    }

}
