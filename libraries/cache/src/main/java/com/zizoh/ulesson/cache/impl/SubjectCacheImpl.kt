package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.SubjectCacheMapper
import com.zizoh.ulesson.cache.room.dao.SubjectDao
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.models.SubjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectCacheImpl @Inject constructor(
    private val dao: SubjectDao,
    private val mapper: SubjectCacheMapper
) : SubjectCache {

    override suspend fun saveSubjects(subjects: List<SubjectEntity>) {
        val subjectsCache = mapper.mapToModelList(subjects)
        dao.saveSubjects(subjectsCache)
    }

    override fun getSubjects(): Flow<List<SubjectEntity>> {
        return dao.getSubjects().map {
            mapper.mapToEntityList(it)
        }
    }
}