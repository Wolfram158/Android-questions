package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import ru.wolfram.kotlin.Container
import java.util.concurrent.Executors
import kotlin.system.exitProcess

class AsCoroutineDispatcher : Container() {
    companion object {
        fun template(n1: Int, n2: Int, t1: Long, t2: Long) {
            val executors = Executors.newFixedThreadPool(n1)
            val dispatcher = executors.asCoroutineDispatcher()
            val scope = CoroutineScope(dispatcher + Job())
            val start = System.currentTimeMillis()
            repeat(n2) {
                scope.launch {
                    Thread.sleep(t1)
                }
            }
            executors.execute {
                Thread.sleep(t2)
                println("Finish: ${System.currentTimeMillis() - start}")
                exitProcess(0)
            }
        }
    }

    fun run0() {
        template(12, 12, 999_999, 1000)
    }

    fun run1() {
        template(12, 11, 999_999, 1000)
    }

    fun run2() {
        template(12, 12, 9000, 1000)
    }
}