package com.ibnu.moviepaging3.data.remote.factory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ibnu.moviepaging3.data.remote.network.MovieService
import com.ibnu.moviepaging3.data.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviePagingSource(private val apiService: MovieService) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val pageNumber = params.key ?: 1
        return try {
            withContext(Dispatchers.IO) {
                val service = apiService.getTopRatedMovies()
                val response = service.body()
                val data = response?.results

                LoadResult.Page(
                    data = data.orEmpty(),
                    prevKey = if (pageNumber > response?.totalPage!!) null else pageNumber - 1,
                    nextKey = if (pageNumber < response.totalPage) null else pageNumber + 1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}