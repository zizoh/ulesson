package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.domain.models.Subject
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class SubjectEntityMapper @Inject constructor(
    private val chapterEntityMapper: ChapterEntityMapper
) : EntityMapper<SubjectEntity, Subject> {

    override fun mapFromEntity(entity: SubjectEntity): Subject {
        return with(entity) {
            Subject(id, name, icon, chapterEntityMapper.mapFromEntityList(chapters))
        }
    }

    override fun mapToEntity(domain: Subject): SubjectEntity {
        return with(domain) {
            SubjectEntity(id, name, icon, chapterEntityMapper.mapFromDomainList(chapters))
        }
    }
}