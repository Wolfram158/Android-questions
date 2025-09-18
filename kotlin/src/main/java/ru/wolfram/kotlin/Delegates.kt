package ru.wolfram.kotlin

import java.util.function.IntFunction

class Delegates : Container() {
    companion object C {
        class LoggedMutableList<T>(private val list: MutableList<T>) : MutableList<T> by list {
            @Suppress("OVERRIDE_DEPRECATION")
            override fun <T : Any?> toArray(p0: IntFunction<Array<out T?>?>): Array<out T?>? {
                return super.toArray(p0)
            }

            override fun add(element: T): Boolean {
                println("Added in $this: $element")
                return list.add(element)
            }

        }
    }

    fun run0() {
        val lml = LoggedMutableList<Int>(mutableListOf())
        repeat(10) {
            lml.add(3 * it + 1)
        }
    }

}
