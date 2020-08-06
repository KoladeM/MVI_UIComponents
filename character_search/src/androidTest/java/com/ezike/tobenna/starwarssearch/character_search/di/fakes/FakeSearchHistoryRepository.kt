package com.ezike.tobenna.starwarssearch.character_search.di.fakes

import com.ezike.tobenna.starwarssearch.domain.model.Character
import com.ezike.tobenna.starwarssearch.domain.repository.SearchHistoryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSearchHistoryRepository @Inject constructor() : SearchHistoryRepository {

    private val cache = LinkedHashMap<String, Character>()

    override suspend fun saveSearch(character: Character) {
        cache[character.url] = character
    }

    override fun getSearchHistory(): Flow<List<Character>> {
        return flowOf(cache.values.toList().reversed())
    }

    override suspend fun clearSearchHistory() {
        cache.clear()
    }
}