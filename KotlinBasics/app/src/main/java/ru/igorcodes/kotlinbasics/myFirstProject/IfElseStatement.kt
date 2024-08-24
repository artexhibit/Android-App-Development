package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {

    print("Please enter a number : ")
    var number: Int = readLine()!!.trim().toInt()

    if (number % 2 == 0) {
        println("$number is an even")
    } else {
        println("$number is an odd")
    }
}