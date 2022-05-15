package com.example.android_newsapp_compose.di

import com.example.android_newsapp_compose.data.remote.ApiService
import com.example.android_newsapp_compose.data.remote.RemoteDataSource
import com.example.android_newsapp_compose.data.repository.Repository
import com.example.android_newsapp_compose.data.utils.NetworkConst
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = NetworkConst.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    @Singleton
    @Provides
    @Named("retrofit")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") url: String,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideApiService(@Named("retrofit") retrofit: Retrofit): ApiService =
        retrofit.create(
            ApiService::class.java
        )


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
    ): Repository {
        return Repository(remoteDataSource)

    }
}