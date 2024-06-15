import java.util.Scanner

fun main(args: Array<String>)  {
    val sc: Scanner = Scanner(System.`in`)

    val n = sc.nextInt()
    sc.close()

    var sum = 0L
    for (i in 1..n) {
        sum += (n / i) * i
    }
    println(sum)
}
