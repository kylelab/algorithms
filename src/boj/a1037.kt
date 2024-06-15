import java.util.Scanner

fun main() {
    val sc: Scanner = Scanner(System.`in`)
    val count = sc.nextInt()

    var max = 0
    var min = 0
    for (i in 1..count) {
        val target = sc.nextInt()
        if (max == 0 && min == 0) {
            max = target
            min = target
            continue
        }

        if (target > max) {
            max = target
        } else if (target < min) {
            min = target
        }
    }
    sc.close()
    println(max * min)
}
