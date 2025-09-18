package ru.wolfram.kotlin

abstract class Container {
    fun run(n: Int = 0) {
        javaClass.getDeclaredMethod("run$n").invoke(this)
    }
}