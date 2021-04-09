package com.aldredo.core.base.di

import com.aldredo.core.base.interceptor.ApiInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkHttpClientModule {
    @Provides
    @Singleton
    fun provideDispatcher() = Dispatcher().apply { maxRequests = 1 }

    @Provides
    @Singleton
    fun provideHttpClientAuth(dispatcher: Dispatcher, interceptor: ApiInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .dispatcher(dispatcher)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClientAuth: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(DOMAIN)
            .client(okHttpClientAuth)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    companion object {
        private const val DOMAIN = "https://development.lookit.hk/api/test"
    }
}
