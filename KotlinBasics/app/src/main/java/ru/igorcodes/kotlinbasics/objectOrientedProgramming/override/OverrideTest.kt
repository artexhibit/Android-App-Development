package ru.igorcodes.objectorientedprogramming.override

fun main(args: Array<String>) {
    //creating an object from Vehicle class
    var vehicle = Vehicle()
    vehicle.start()
    vehicle.accelerate(80)
    vehicle.stop()

    println("--------------------------")

    // creating an object from Car class
    var car = Car()

    car.superStart()
    car.superAccelerate()
    car.superStop()

    println("--------------------------")

    car.start()
    car.accelerate(100)
    car.stop()

}