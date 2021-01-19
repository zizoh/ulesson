package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.SubjectCacheMapper
import com.zizoh.ulesson.cache.room.dao.SubjectDao
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.models.SubjectEntity
import javax.inject.Inject

class SubjectCacheImpl @Inject constructor(
    private val dao: SubjectDao,
    private val mapper: SubjectCacheMapper
) : SubjectCache {

    override suspend fun saveSubjects(subjects: List<SubjectEntity>) {
        val subjectsCache = mapper.mapToModelList(subjects)
        dao.saveSubjects(subjectsCache)
    }

    override suspend fun getSubjects(): List<SubjectEntity> {
        val subjectsCache = dao.getSubjects()
        return mapper.mapToEntityList(subjectsCache)
    }
}