package ru.igorcodes.objectorientedprogramming.interfaces

fun main(args: Array<String>) {
    var vehicles = Vehicle()

    println("Name: ${vehicles.name}")
    vehicles.go()
    vehicles.stop()
}