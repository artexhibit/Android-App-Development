package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {
    var age = ArrayList<Int>()
    age.add(10)
    age.add(1, 15)
    age.add(20)

//    println("First element of Array List = " + age[0])
//    println("Second element of Array List = " + age.get(1))
//    println("Third element of Array List = ${age[2]}")

    age.remove(15)

    println(age.size)
    println("First element of Array List = " + age[0])
    println("Second element of Array List = " + age.get(1))

    var cars = arrayListOf<String>("Opel", "BMW")
    cars.add("Ford")

    println("First element of Array List = " + cars[0])
    println("Second element of Array List = " + cars.get(1))
    println("Third element of Array List = ${cars[2]}")

    var myMixArrayList = ArrayList<Any>()
    myMixArrayList.add("Ford")
    myMixArrayList.add(5)
    myMixArrayList.add(2.5)
    myMixArrayList.add(false)
    myMixArrayList.add('K')

    println(myMixArrayList[0])
    println(myMixArrayList[1])
    println(myMixArrayList[2])
    println(myMixArrayList[3])
    println(myMixArrayList[4])
}