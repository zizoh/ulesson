package com.zizoh.ulesson.domain.repository

import com.zizoh.ulesson.domain.models.Subject
import kotlinx.coroutines.flow.Flow

/**
 * Created by zizoh on 15/January/2021.
 */

interface SubjectRepository {

    fun getSubjects(): Flow<List<Subject>>
}