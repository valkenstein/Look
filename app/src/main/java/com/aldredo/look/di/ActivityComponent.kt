package com.aldredo.look.di

import com.aldredo.core.base.di.CoreComponent
import com.aldredo.look.presentation.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ApiModule::class]
)
interface ActivityComponent {
    fun inject(app: MainActivity)

    companion object {
        fun create(context: MainActivity): ActivityComponent =
            DaggerActivityComponent
                .builder()
                .coreComponent(CoreComponent.create(context.application))
                .build()

    }
}