package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import ru.wolfram.kotlin.Container
import kotlin.time.Duration.Companion.seconds

class Cancellation1 : Container() {
    companion object {
        suspend fun doWork() {
            delay(500)
            throw RuntimeException("Didn't work!")
        }
    }

    fun run0() {
        runBlocking {
            withTimeoutOrNull(2.seconds) {
                while (true) {
                    try {
                        doWork()
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }

    fun run1() {
        runBlocking {
            withTimeoutOrNull(2.seconds) {
                while (true) {
                    try {
                        doWork()
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }
}