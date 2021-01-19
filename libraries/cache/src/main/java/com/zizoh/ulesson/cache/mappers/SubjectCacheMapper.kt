package com.zizoh.ulesson.cache.mappers

import com.zizoh.ulesson.cache.mappers.base.CacheModelMapper
import com.zizoh.ulesson.cache.models.SubjectCacheModel
import com.zizoh.ulesson.data.models.SubjectEntity
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class SubjectCacheMapper @Inject constructor() :
    CacheModelMapper<SubjectCacheModel, SubjectEntity> {

    override fun mapToEntity(model: SubjectCacheModel): SubjectEntity {
        return with(model) {
            SubjectEntity(subjectId, name, icon, emptyList())
        }
    }

    override fun mapToModel(entity: SubjectEntity): SubjectCacheModel {
        return with(entity) {
            SubjectCacheModel(id, name, icon)
        }
    }
}