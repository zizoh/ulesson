package com.zizoh.ulesson.dashboard.presentation.mappers

import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.presentation.mapper.ModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class LessonModelMapper @Inject constructor() : ModelMapper<LessonModel, Lesson> {

    override fun mapToModel(domain: Lesson): LessonModel {
        return with(domain) {
            LessonModel(id, name, icon, mediaUrl, subjectId, chapterId)
        }
    }

    override fun mapToDomain(model: LessonModel): Lesson {
        return with(model) {
            Lesson(id, name, icon, mediaUrl, subjectId, chapterId)
        }
    }
}