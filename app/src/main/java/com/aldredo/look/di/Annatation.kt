package com.aldredo.look.di

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TaskWindowScope