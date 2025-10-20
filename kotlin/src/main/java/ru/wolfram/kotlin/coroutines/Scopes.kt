package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

object Scopes {
    fun provideScope1() =
        CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher() + Job())

    fun provideScope2() =
        CoroutineScope(Executors.newFixedThreadPool(8).asCoroutineDispatcher() + Job())

    fun provideScope3() =
        CoroutineScope(Dispatchers.IO + SupervisorJob())
}