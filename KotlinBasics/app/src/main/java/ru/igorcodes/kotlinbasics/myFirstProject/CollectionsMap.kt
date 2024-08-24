package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {
    //immutable

    println("-----------immutable map of------------")
    var age = mapOf<String,Int>("david" to 20, "ronaldo" to 25)
    println("age of David : " + age["david"])
    println("age of Ronaldo : " + age["ronaldo"])

    //mutable

    println("-----------mutable map of------------")
    var mutableAge = mutableMapOf<String,Int>("david" to 20, "ronaldo" to 25)
    mutableAge.put("buffon", 30)

    println("age of David : " + mutableAge["david"])
    println("age of Ronaldo : " + mutableAge["ronaldo"])
    println("age of Buffon : " + mutableAge.get("buffon"))


}