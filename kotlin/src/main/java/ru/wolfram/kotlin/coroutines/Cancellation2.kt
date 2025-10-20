package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import ru.wolfram.kotlin.Container

class Cancellation2 : Container() {
    companion object {
        suspend fun doCpuHeavyWork1(): Int {
            println("I'm doing work!")
            var counter = 0
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < startTime + 500) {
                counter++
            }
            return counter
        }

        suspend fun doCpuHeavyWork2(): Int {
            println("I'm doing work!")
            var counter = 0
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < startTime + 400) {
                delay(100)
                counter++
            }
            return counter
        }

        suspend fun doCpuHeavyWork3(): Int {
            println("I'm doing work!")
            var counter = 0
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < startTime + 500) {
                yield()
                counter++
            }
            return counter
        }

        suspend fun doCpuHeavyWork4(name: String): Int {
            println("$name is doing work!")
            var counter = 0
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < startTime + 500) {
                counter++
            }
            return counter
        }

        suspend fun doCpuHeavyWork5(name: String): Int {
            println("$name is doing work!")
            var counter = 0
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < startTime + 500) {
                yield()
                counter++
            }
            return counter
        }
    }

    fun run0() {
        runBlocking {
            val myJob = launch {
                repeat(5) {
                    doCpuHeavyWork1()
                }
            }
            delay(600)
            myJob.cancel()
        }
    }

    fun run1() {
        runBlocking {
            val myJob = launch {
                repeat(5) {
                    doCpuHeavyWork2()
                }
            }
            delay(600)
            myJob.cancel()
        }
    }

    fun run2() {
        runBlocking {
            val myJob = launch {
                repeat(5) {
                    doCpuHeavyWork3()
                }
            }
            delay(600)
            myJob.cancel()
        }
    }

    fun run3() {
        runBlocking {
            launch {
                repeat(5) {
                    doCpuHeavyWork4("1")
                }
            }
            launch {
                repeat(5) {
                    doCpuHeavyWork4("2")
                }
            }
        }
    }

    fun run4() {
        runBlocking {
            launch {
                repeat(3) {
                    doCpuHeavyWork5("1")
                }
            }
            launch {
                repeat(3) {
                    doCpuHeavyWork5("2")
                }
            }
        }
    }
}