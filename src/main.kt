package cinema

fun printCinema (cinema: Array<Array<Char>>, rows: Int, seats: Int) {
    println("Cinema:")
    for (i in 1..rows) {
        if (i == 1) {
            print("  ")
            for (k in 1..seats) {
                print(k)
                if (k < seats) {
                    print(' ')
                }
            }
            println()
        }
        print("$i ")
        for (j in 1..seats) {
            print(cinema[i - 1][j - 1])
            if (j < seats) {
                print(' ')
            }
        }
        println()
    }
    println()
}

fun main() {
    println("Enter the number of rows:")
    println("> ")
    val rows: Int = readLine()!!.toInt()

    println("Enter the number of seats in each row:")
    println("> ")
    val seats: Int = readLine()!!.toInt()
    println()

    val totalSeats: Int = rows * seats
    val totalIncome: Int =  if (totalSeats <= 60) totalSeats * 10 else (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8
    val cinema = Array(rows) {Array(seats) {'S'} }
    var purchased: Int = 0
    var percentage: Double = 0.0
    var currentIncome: Int = 0

    while (true) {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        println("> ")

        val n: Int = readLine()!!.toInt()
        if (n == 1) {
            printCinema(cinema, rows, seats)
        } else if (n == 2) {
            while (true) {
                println("Enter a row number:")
                println("> ")
                val row: Int = readLine()!!.toInt()

                println("Enter a seat number in that row:")
                println("> ")
                val seat: Int = readLine()!!.toInt()
                println()

                if (row > rows || seat > seats) {
                    println("Wrong input!")
                    println()
                } else if (cinema[row - 1][seat - 1] == 'B') {
                    println("That ticket has already been purchased!")
                    println()
                } else {
                    cinema[row - 1][seat - 1] = 'B'

                    if (totalSeats <= 60) {
                        println("Ticket price: \$10")
                        currentIncome += 10
                    } else {
                        println("Ticket price: \$${if (row <= rows / 2) 10 else 8}")
                        currentIncome += if (row <= rows / 2) 10 else 8
                    }
                    println()

                    purchased += 1
                    percentage = (purchased.toDouble() / totalSeats.toDouble()) * 100.0

                    printCinema(cinema, rows, seats)
                    break
                }
            }
        } else if (n == 3) {
            println()
            println("Number of purchased tickets: $purchased")
            print("Percentage: ")
            print(" %.2f".format(percentage))
            println("%")
            println("Current income: \$$currentIncome")
            println("Total income: \$$totalIncome")
            println()
        }else if (n == 0) {
            break
        }
    }
}