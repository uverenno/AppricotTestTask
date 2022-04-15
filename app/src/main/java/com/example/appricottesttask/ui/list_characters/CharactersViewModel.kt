package com.example.appricottesttask.ui.list_characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.Character
import com.example.data.remote.RickAndMortyPagingSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class CharactersViewModel(
    rickAndMortyPagingSource: RickAndMortyPagingSource
) : ViewModel() {

    val characters: StateFlow<PagingData<Character>> = Pager(PagingConfig(pageSize = 20)) {
        rickAndMortyPagingSource
    }.flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    class Factory @Inject constructor(private val rickAndMortyPagingSource: Provider<RickAndMortyPagingSource>) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CharactersViewModel::class.java)
            return CharactersViewModel(rickAndMortyPagingSource = rickAndMortyPagingSource.get()) as T
        }

    }

}