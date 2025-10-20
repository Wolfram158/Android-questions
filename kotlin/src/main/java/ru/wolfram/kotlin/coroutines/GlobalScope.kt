package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.wolfram.kotlin.Container

class GlobalScope : Container() {
    @OptIn(DelicateCoroutinesApi::class)
    fun run0() {
        runBlocking {
            GlobalScope.launch {
                launch {
                    delay(1000)
                    println("End 1")
                }
            }
            GlobalScope.launch {
                launch {
                    delay(2000)
                    println("End 2")
                }
            }
        }
    }

    fun run1() {
        val scope = Scopes.provideScope3()
        runBlocking {
            scope.launch {
                launch {
                    delay(1000)
                    println("End 1")
                }
            }
            scope.launch {
                launch {
                    delay(2000)
                    println("End 2")
                }
            }
        }
    }

    fun run2() {
        runBlocking {
            launch {
                launch {
                    delay(1000)
                    println("End 1")
                }
            }
            launch {
                launch {
                    delay(2000)
                    println("End 2")
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun run3() = runBlocking {
        GlobalScope.launch {
            launch {
                delay(2000)
                println("1")
                throw RuntimeException("Exception!")
            }
            launch {
                delay(5000)
                println("2")
            }
        }
        delay(7500)
    }
}