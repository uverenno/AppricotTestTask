package com.example.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.model.Character
import retrofit2.HttpException

class RickAndMortyPagingSource(
    private val service: CharactersService
) : PagingSource<Int,Character>() {


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = service.getAllCharacters(pageNumber)
            if (response.isSuccessful) {
                val articles = response.body()!!.results
                val nextPageNumber = if (articles.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                return LoadResult.Page(articles, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}