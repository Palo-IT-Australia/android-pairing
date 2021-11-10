package com.paloit.coin.di

import android.content.Context
import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.api.Constants.Api.CONNECT_TIMEOUT_IN_S
import com.paloit.coin.platform.api.Constants.Api.READ_TIMEOUT_IN_S
import com.paloit.coin.platform.api.Constants.Api.WRITE_TIMEOUT_IN_S
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, moshi: Moshi): BpiApi = Retrofit.Builder()
        .baseUrl(BpiApi.END_POINT)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(BpiApi::class.java)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}
