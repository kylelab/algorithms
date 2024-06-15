import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import kotlin.math.max

fun main(args: Array<String>){
    args.toString()

    val maxSize = 1_000_000
    val primeArray = getPrimeArray(maxSize)

    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    while(true){
        val n:Int = Integer.parseInt(br.readLine())
        if(n == 0) break

        printAnswer(n, primeArray, maxSize)
    }
}

fun getPrimeArray(max: Int): BooleanArray {
    val primes = BooleanArray(max+1) { true }
    primes[0] = false
    primes[1] = false

    var index = 2
    while(index * index<=max){
        if(primes[index]) {
            var j = index*index
            while(j <= max){
                primes[j] = false
                j += index
            }
        }
        index++
    }

    return primes
}

fun printAnswer(n: Int, primeArray: BooleanArray, maxSize: Int) {
    for(i in 3..maxSize) {
        val isAPrime = primeArray[i]
        val b = n - i // n-b = a b와 a가 모두 소수 이기만 하면됨
        if(isAPrime && primeArray[b]){
            println("$n = $i + $b")
            break
        }
    }
}
