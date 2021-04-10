package com.aldredo.look.di

import com.aldredo.look.data.api.CodeApi
import com.aldredo.look.data.api.PingApi
import com.aldredo.look.data.api.ProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {
    @Provides
    @ActivityScope
    fun providePingApi(retrofit: Retrofit): PingApi {
        return retrofit.create(PingApi::class.java)
    }

    @Provides
    @ActivityScope
    fun provideCodeApi(retrofit: Retrofit): CodeApi {
        return retrofit.create(CodeApi::class.java)
    }

    @Provides
    @ActivityScope
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
}