package com.ibnu.moviepaging3.data.remote

import com.ibnu.moviepaging3.data.remote.network.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApp {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_KEY = "83bd87b6c77400db6e99add8541fcc63"
        const val FIRST_PAGE = 1
        const val POSTER_PATH = "https://image.tmdb.org/t/p/original"

        private val movieInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(movieInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(150, TimeUnit.SECONDS)
                .build()
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        fun getMovieService(): MovieService {
            return retrofit.create(MovieService::class.java)
        }
    }
}