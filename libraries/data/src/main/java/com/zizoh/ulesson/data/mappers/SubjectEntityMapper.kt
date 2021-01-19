package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.domain.models.Subject
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class SubjectEntityMapper @Inject constructor(
) : EntityMapper<SubjectEntity, Subject> {

    override fun mapFromEntity(entity: SubjectEntity): Subject {
        return with(entity) {
            Subject(id, name, icon, emptyList())
        }
    }

    override fun mapToEntity(domain: Subject): SubjectEntity {
        throw IllegalStateException("Not mapping to entity")
    }
}