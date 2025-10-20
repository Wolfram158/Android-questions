package ru.wolfram.kotlin

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.wolfram.kotlin.coroutines.AsCoroutineDispatcher
import ru.wolfram.kotlin.coroutines.Cancellation1
import ru.wolfram.kotlin.coroutines.Cancellation2
import ru.wolfram.kotlin.coroutines.ChannelFlow
import ru.wolfram.kotlin.coroutines.Coroutines1
import ru.wolfram.kotlin.coroutines.GlobalScope

object MainConsoleApplication : KoinComponent {
    val module = module {
        single<Container>(named(Examples.DELEGATES)) { Delegates() }
        single<Container>(named(Examples.SEQUENCES)) { Sequences() }
        single<Container>(named(Examples.INIT)) { Init() }
        single<Container>(named(Examples.CLASH_DECLARATION)) { ClashDeclaration() }
        single<Container>(named(Examples.COROUTINES_1)) { Coroutines1() }
        single<Container>(named(Examples.GLOBAL_SCOPE)) { GlobalScope() }
        single<Container>(named(Examples.CANCELLATION_1)) { Cancellation1() }
        single<Container>(named(Examples.CANCELLATION_2)) { Cancellation2() }
        single<Container>(named(Examples.AS_COROUTINE_DISPATCHER)) { AsCoroutineDispatcher() }
        single<Container>(named(Examples.CHANNEL_FLOW)) { ChannelFlow() }
    }

    init {
        startKoin {
            modules(listOf(module))
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val container: Container by inject(named(Examples.CHANNEL_FLOW))
        container.run(2)
    }
}