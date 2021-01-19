package com.zizoh.ulesson.cache.room.dao

import androidx.room.*
import com.zizoh.ulesson.cache.models.ChapterCacheModel
import com.zizoh.ulesson.cache.models.ChapterWithLessons

/**
 * Created by zizoh on 18/January/2021.
 */

@Dao
interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChapters(chapters: List<ChapterCacheModel>)

    @Transaction
    @Query("SELECT * FROM  chapters WHERE subjectId=:subjectId")
    fun getChapterWithLessonsWithSubjectId(subjectId: Int): List<ChapterWithLessons>
}