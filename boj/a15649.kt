import java.io.BufferedReader;
import java.io.InputStreamReader;
import kotlin.collections.single
import kotlin.collections.map
import kotlin.intArrayOf

/**
 * Problem: https://www.acmicpc.net/problem/15649
 * Algorithm: 브루트 포스 N과 M 재귀
 * TimeComplexity: O(N!)???
 * Solution
 * 
 * 
 */
fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    br.close()

    println(getDigits(n))
}

fun getDigits(n: Int): Int {
    var len = 1
    var start = 1
    var end = 0
    var ans = 0
    while(start <= n) {
        end = start*10-1

        if(end>n){
            end = n
        }

        ans += (end - start + 1)*len

        start *=10
        len++
    }

    return ans
}