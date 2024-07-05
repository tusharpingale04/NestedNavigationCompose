package com.tushar.moviescompose.di

import com.tushar.moviescompose.network.HeaderInterceptor
import com.tushar.moviescompose.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://moviesdatabase.p.rapidapi.com/"
    }

    @Provides
    fun provideInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Provides
    fun provideOKHTTPClient(
        interceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMoviesService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

}