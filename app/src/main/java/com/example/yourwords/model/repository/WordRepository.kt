package com.example.yourwords.model.repository

import com.example.yourwords.entity.Word
import com.example.yourwords.model.dao.WordDao

class WordRepository(private val dao: WordDao) {

    suspend fun getAll(): List<Word> = dao.getAll().sortedBy {
        it.value
    }

    suspend fun findByValue(value: String) = dao.findByValue(value)

    suspend fun getAllId() = dao.getAllId()

    suspend fun getRandom(limit: Int = 1, selected: String = "") = dao.getRandom(limit, selected)

    suspend fun addWord(word: Word) = dao.insert(word)

    suspend fun deleteWord(word: Word) = dao.delete(word)
}