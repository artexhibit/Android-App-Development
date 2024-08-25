package ru.igorcodes.objectorientedprogramming

class MySecondCars {
    var name: String? = null
    var model: Int? = null
        private set

    constructor(model: Int?, name: String?) {
        this.model = model
        this.name = name
    }
}