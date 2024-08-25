package ru.igorcodes.objectorientedprogramming.interfaces

class Vehicle: CanGo, CanStop {
    override val name: String
        get() = "Ferrari"

    override fun stop() {
        println("Vehicles can stop")
    }
}