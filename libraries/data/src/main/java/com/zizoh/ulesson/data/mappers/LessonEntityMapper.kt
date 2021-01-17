package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.LessonEntity
import com.zizoh.ulesson.domain.models.Lesson
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class LessonEntityMapper @Inject constructor() : EntityMapper<LessonEntity, Lesson> {

    override fun mapFromEntity(entity: LessonEntity): Lesson {
        return with(entity) {
            Lesson(id, name, icon, mediaUrl, subjectId, subjectName, chapterId)
        }
    }

    override fun mapToEntity(domain: Lesson): LessonEntity {
        return with(domain) {
            LessonEntity(id, name, icon, mediaUrl, subjectId, subjectName, chapterId)
        }
    }
}