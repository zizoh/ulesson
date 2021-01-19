package com.zizoh.ulesson.data.contract.remote

import com.zizoh.ulesson.data.models.SubjectEntity

/**
 * Created by zizoh on 15/January/2021.
 */

interface SubjectRemote {

    suspend fun getSubjects(): List<SubjectEntity>
}