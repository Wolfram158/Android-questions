package ru.wolfram.kotlin

import java.io.File
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class Sequences : Container() {
    fun run0() {
        val numbers = IntRange(1, 112_500_000).map { Random.nextInt() }

        fun Iterable<Int>.actions() {
            filter { it > 500 }.map { it / 10 }
                .filter { it > 100 && it < 10000000 }.take(100000).sum().also {
                    println(it)
                }
        }

        fun Sequence<Int>.actions() {
            filter { it > 500 }.map { it / 10 }
                .filter { it > 100 && it < 10000000 }.take(100000).sum().also {
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

    fun run1() {
        fun File.isInsideHiddenDirectory() =
            generateSequence(this) { it.parentFile }.any { it.isHidden }

        val file = File("/Users/user/.HiddenDir/a.txt")
        println(file.isInsideHiddenDirectory())
    }

}