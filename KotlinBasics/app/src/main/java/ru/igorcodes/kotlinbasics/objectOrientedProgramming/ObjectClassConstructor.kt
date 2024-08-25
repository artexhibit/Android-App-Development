package ru.igorcodes.objectorientedprogramming

fun main(args: Array<String>) {

//creating an object from class
//    var myCar = Cars()
//    myCar.name = "Ferrari"
//    myCar.model = 2021
//
//    var myCar2 = Cars()
//    myCar2.name = "Mercedes"
//    myCar2.model = 2010
//
//    println("My first car's name is ${myCar.name} and it's model is ${myCar.model}")
//    println("My second car's name is ${myCar2.name} and it's model is ${myCar2.model}")

//   var myNewCar = MyCars("Ferrari", 2021)
//   println("My car's name is ${myNewCar.name} and it's model is ${myNewCar.model}")

    var mySecondCar = MySecondCars(2000, "Mercedes")
    mySecondCar.name = "Opel"
   //mySecondCar.model = 2010 //set

    println("My car's name is ${mySecondCar.name} and it's model is ${mySecondCar.model}") //get
}