package ru.wolfram.kotlin

import kotlin.system.measureTimeMillis

class Init : Container() {
    companion object {
        class WithInit(
            isSleep: Boolean = true
        ) {
            private val map = HashMap<String, Int>()

            init {
                if (isSleep) {
                    Thread.sleep(5000L)
                }
                map.putAll(
                    mapOf(
                        "John" to 2500,
                        "Carl" to 1500,
                        "Sam" to 3750,
                        "Paul" to 750,
                        "Andrew" to 7000
                    )
                )
            }

            fun printlnMapSize() {
                println("Map size: ${map.size}")
            }
        }
    }

    fun run0() {
        lateinit var withInit: WithInit
        val time = measureTimeMillis { withInit = WithInit() }
        println("Time: $time")
        withInit.printlnMapSize()
    }
}