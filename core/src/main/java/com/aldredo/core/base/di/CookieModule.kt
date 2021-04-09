package com.aldredo.core.base.di

import com.aldredo.core.base.interceptor.model.CookieModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CookieModule {
    @Provides
    @Singleton
    fun provideManagerToken(): CookieModel {
        return CookieModel()
    }
}