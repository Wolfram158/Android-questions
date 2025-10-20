package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.wolfram.kotlin.Container
import kotlin.system.exitProcess

class Coroutines1 : Container() {
    fun run0() {
        val scope = Scopes.provideScope1()
        runBlocking {
            var x = 0L
            val sixBilliard = 6E9.toInt()
            scope.launch {
                repeat(sixBilliard) {
                    x++
                }
            }
            scope.launch {
                repeat(sixBilliard) {
                    x++
                }
            }.join()
            println(x)
            exitProcess(0)
        }
    }

    fun run1() {
        val scope = Scopes.provideScope2()
        runBlocking {
            var x = 0L
            val sixBilliard = 6E9.toInt()
            scope.launch {
                repeat(sixBilliard) {
                    x++
                }
            }
            scope.launch {
                repeat(sixBilliard) {
                    x++
                }
            }.join()
            println(x)
            exitProcess(0)
        }
    }

    fun run2() {
        val scope = Scopes.provideScope2()
        val mutex = Mutex()
        runBlocking {
            var x = 0L
            val sixBilliard = 6E9.toInt()
            scope.launch {
                repeat(sixBilliard) {
                    mutex.withLock {
                        x++
                    }
                }
            }
            scope.launch {
                repeat(sixBilliard) {
                    mutex.withLock {
                        x++
                    }
                }
            }.join()
            println(x)
            exitProcess(0)
        }
    }
}