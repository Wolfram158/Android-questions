package ru.wolfram.kotlin

class ClashDeclaration : Container() {
    companion object {
        class X {
            @get:JvmName("getY")
            @set:JvmName("setY")
            var x = 3

            fun setX(x: Int) {
                this.x = x
            }

            fun getX() = x
        }
    }

    fun run0() {
        val x = X()
        println(x.x)
        x.setX(103)
        println(x.x)
    }
}