package com.aldredo.look.di

import com.aldredo.core.base.di.CoreComponent
import com.aldredo.look.presentation.activity.MainActivity
import dagger.Component

@TaskWindowScope
@Component(
    dependencies = [CoreComponent::class]
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

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun setActivity(context: Context): Builder
//
//        fun setAppComponent(app: AppComponent): Builder
//
//        fun build(): ActivityComponent
//    }
}