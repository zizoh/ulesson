package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.LessonEntity
import com.zizoh.ulesson.domain.models.Lesson
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class LessonEntityMapper @Inject constructor(
) : EntityMapper<LessonEntity, Lesson> {

    override fun mapFromEntity(entity: LessonEntity): Lesson {
        return with(entity) {
            Lesson(id, name, icon, mediaUrl, subjectId, chapterId, chapterName)
        }
    }

    override fun mapToEntity(domain: Lesson): LessonEntity {
        throw IllegalStateException("Not mapping to entity")
    }
}