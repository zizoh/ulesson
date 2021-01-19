package com.zizoh.ulesson.cache.room.dao

import androidx.room.*
import com.zizoh.ulesson.cache.models.SubjectCacheModel

/**
 * Created by zizoh on 18/January/2021.
 */

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSubjects(subjects: List<SubjectCacheModel>)

    @Transaction
    @Query("SELECT * FROM  subjects")
    fun getSubjects(): List<SubjectCacheModel>
}