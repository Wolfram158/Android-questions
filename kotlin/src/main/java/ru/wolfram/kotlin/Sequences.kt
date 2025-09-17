package ru.wolfram.kotlin

import kotlin.random.Random
import kotlin.system.measureTimeMillis

class Sequences : Container {
    override fun run() {
        val numbers = IntRange(1, 112_500_000).map { Random.nextInt() }

        fun Iterable<Int>.actions() {
            filter { it > 500 }.map { it / 10 }
                .filter { it > 100 && it < 100000 }.take(50).sum().also {
                    println(it)
                }
        }

        fun Sequence<Int>.actions() {
            filter { it > 500 }.map { it / 10 }
                .filter { it > 100 && it < 100000 }.take(50).sum().also {
                    println(it)
                }
        }

        val time1 = measureTimeMillis {
            numbers.asSequence().actions()
        }

        val time2 = measureTimeMillis {
            numbers.actions()
        }

        println("$time1 $time2")
    }
}