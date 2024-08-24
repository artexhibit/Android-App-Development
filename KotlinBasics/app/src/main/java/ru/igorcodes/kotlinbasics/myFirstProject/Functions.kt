package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {

    print("Please enter the first number: ")
    var num1: Int = readLine()!!.trim().toInt()
    print("Please enter the second number: ")
    var num2: Int = readLine()!!.trim().toInt()

    show(num1, num2)
    var a = add(num1, num2)
    println("Sum of the two numbers = $a")
    var b = findMinimumNumber(num1, num2)
    println("The minimum number is = $b")

}

fun show(num1: Int, num2: Int) {
    println("You entered $num1 and $num2")
}

fun add(num1: Int, num2: Int) : Int {
    var sum: Int = 0
    sum = num1 + num2
    return  sum
}

fun findMinimumNumber(num1: Int, num2: Int) : Int {
    var minimum: Int

    if (num1 > num2) {
        minimum = num2
    } else {
        minimum = num1
    }
    return minimum
}