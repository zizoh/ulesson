package com.zizoh.ulesson.cache.room.dao

import androidx.room.*
import com.zizoh.ulesson.cache.models.LessonCacheModel

/**
 * Created by zizoh on 18/January/2021.
 */

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLessons(lessons: List<LessonCacheModel>)

    @Transaction
    @Query("SELECT * FROM  lessons WHERE id=:id")
    fun getLessonWithId(id: Int): LessonCacheModel
}