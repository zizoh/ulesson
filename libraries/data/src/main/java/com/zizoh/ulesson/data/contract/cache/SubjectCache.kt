package com.zizoh.ulesson.data.contract.cache

import com.zizoh.ulesson.data.models.SubjectEntity

/**
 * Created by zizoh on 19/January/2021.
 */

interface SubjectCache {

    suspend fun saveSubjects(subjects: List<SubjectEntity>)

    suspend fun getSubjects(): List<SubjectEntity>

}