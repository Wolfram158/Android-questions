package ru.wolfram.kotlin

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MainConsoleApplication : KoinComponent {
    val module = module {
        single<Container>(named(Examples.DELEGATES)) { Delegates() }
    }

    init {
        startKoin {
            modules(listOf(module))
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val container: Container by inject(named(Examples.DELEGATES))
        container.run()
    }
}