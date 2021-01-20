package com.zizoh.ulesson.cache.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.zizoh.ulesson.cache.factory.DataDataFactory
import com.zizoh.ulesson.cache.models.SubjectCacheModel
import com.zizoh.ulesson.cache.room.ULessonDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by zizoh on 20/January/2021.
 */

@RunWith(AndroidJUnit4::class)
class SubjectDaoTest {

    private lateinit var sut: SubjectDao
    private lateinit var database: ULessonDatabase

    @Before
    fun setup() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ULessonDatabase::class.java
        ).allowMainThreadQueries().build()

        sut = database.subjectDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `saveSubjects inserts subjects`() = runBlocking {
        val expected: List<SubjectCacheModel> = DataDataFactory.makeSubjectCacheModels(7)

        sut.saveSubjects(expected)

        val actual = sut.getSubjects().first()
        assertThat(actual).isNotEmpty()
    }

    @Test
    fun `getSubjects returns empty list if no subjects have been saved`() = runBlocking {
        val subjects = sut.getSubjects().first()

        assertThat(subjects).isEmpty()
    }

    @Test
    fun `getSubjects returns subjects if getSubjects have been saved`() = runBlocking {
        val expected: List<SubjectCacheModel> = DataDataFactory.makeSubjectCacheModels(7)
        sut.saveSubjects(expected)

        val actual = sut.getSubjects().first()

        assertThat(actual).isNotEmpty()
    }

}