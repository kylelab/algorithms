import java.util.Scanner

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)

    val x = sc.nextInt()
    val y = sc.nextInt()
    sc.close()

    println(gcd(x, y))
    println(lcm(x, y))
}

fun gcd(x: Int, y: Int):Int {
    return if(y == 0) {
        x
    } else {
        gcd(y, x%y) // GCD(a, b) = GCD(b, r)// a%b = r
    }
}

fun lcm(x: Int, y: Int):Int {
    val gcd = gcd(x, y)
    return x * y / gcd  // LCM(A, B) = G * (A/G) * (B/G) == A * B/ gcd
}