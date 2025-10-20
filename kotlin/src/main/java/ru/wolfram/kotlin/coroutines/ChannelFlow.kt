package ru.wolfram.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import ru.wolfram.kotlin.Container
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class ChannelFlow : Container() {
    companion object {
        suspend fun getRandomNumber(): Int {
            delay(500)
            return Random.nextInt()
        }
    }

    fun run0() {
        val randomNumbers = flow {
            repeat(10) {
                emit(getRandomNumber())
            }
        }
        val time = measureTimeMillis {
            runBlocking {
                randomNumbers.collect { number ->
                    println(number)
                }
            }
        }
        println("Time: $time ms")
    }

    fun run1() {
        val randomNumbers = flow {
            supervisorScope {
                repeat(10) {
                    launch {
                        emit(getRandomNumber())
                    }
                }
            }
        }
        val time = measureTimeMillis {
            runBlocking {
                randomNumbers.collect { number ->
                    println(number)
                }
            }
        }
        println("Time: $time ms")
    }

    fun run2() {
        val randomNumbers = channelFlow {
            repeat(10) {
                launch {
                    send(getRandomNumber())
                }
            }
        }
        val time = measureTimeMillis {
            runBlocking {
                randomNumbers.collect { number ->
                    println(number)
                }
            }
        }
        println("Time: $time ms")
    }
}