package com.zizoh.ulesson.cache.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.zizoh.ulesson.cache.factory.DataDataFactory
import com.zizoh.ulesson.cache.models.LessonCacheModel
import com.zizoh.ulesson.cache.room.ULessonDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by zizoh on 20/January/2021.
 */

@RunWith(AndroidJUnit4::class)
class LessonDaoTest {

    private lateinit var sut: LessonDao
    private lateinit var database: ULessonDatabase

    @Before
    fun setup() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ULessonDatabase::class.java
        ).allowMainThreadQueries().build()

        sut = database.lessonDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `saveLessons inserts lessons`() = runBlocking {
        val lessons: List<LessonCacheModel> = DataDataFactory.makeLessonCacheModels(7)
        val expected = lessons.random()

        sut.saveLessons(lessons)

        val actual = sut.getLessonWithId(expected.id)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `getSubjects returns subjects if getSubjects have been saved`() = runBlocking {
        val lessons: List<LessonCacheModel> = DataDataFactory.makeLessonCacheModels(7)
        val expected = lessons.random()
        sut.saveLessons(lessons)

        val actual = sut.getLessonWithId(expected.id)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.icon).isEqualTo(expected.icon)
        assertThat(actual.mediaUrl).isEqualTo(expected.mediaUrl)
        assertThat(actual.subjectId).isEqualTo(expected.subjectId)
        assertThat(actual.subjectName).isEqualTo(expected.subjectName)

    }

}