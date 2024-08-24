package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {
    var myCharRange = 'a'.rangeTo('k')
    var myChar = 'k' in myCharRange

    println("myCharRange has k : " + myChar)
}