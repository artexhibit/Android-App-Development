package ru.igorcodes.objectorientedprogramming.interfaces

interface CanGo {
    fun go() {
        println("Vehicles can go!")
    }
    val name: String
}