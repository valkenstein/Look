package com.aldredo.core.base.di

import android.app.Application
import com.aldredo.core.base.room.AppDatabase
import com.aldredo.core.base.interceptor.model.CookieModel
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        OkHttpClientModule::class,
        CookieModule::class,
        DataBaseRoom::class]
)
interface CoreComponent {
    fun provideRetrofit(): Retrofit

    fun provideRoom(): AppDatabase

    fun provideCookieModel(): CookieModel

    companion object {
        fun create(context: Application) =
            DaggerCoreComponent
                .builder()
                .setActivity(context)
                .build()
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(context: Application): Builder

        fun build(): CoreComponent
    }
}


