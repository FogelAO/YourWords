package com.example.yourwords.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yourwords.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM word WHERE value = :first LIMIT 1")
    suspend fun findByValue(first: String): Word

    @Query("SELECT id FROM word")
    suspend fun getAllId(): List<Int>

    @Query("SELECT DISTINCT * FROM word WHERE translate != :second ORDER BY RANDOM() LIMIT :first")
    suspend fun getRandom(first: Int, second: String): List<Word>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)
}