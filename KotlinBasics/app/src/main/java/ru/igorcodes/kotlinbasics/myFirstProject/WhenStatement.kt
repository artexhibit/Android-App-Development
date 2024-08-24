package ru.igorcodes.myfirstproject

fun main(args: Array<String>) {
    print("Please enter the day number of the week: ")
    var dayNumber: Int = readLine()!!.trim().toInt()
    var day: String

    when(dayNumber) {
        1 -> day = "Monday"
        2 -> day = "Tuesday"
        3 -> day = "Wednesday"
        4 -> day = "Thursday"
        5 -> day = "Friday"
        6 -> day = "Saturday"
        7 -> day = "Sunday"
        else -> day = "Invalid day choice"
    }

    println("Day is $day")
}