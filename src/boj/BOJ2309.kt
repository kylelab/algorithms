package boj

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Problem: https://www.acmicpc.net/problem/2309
 * TimeComplexity: O(N^2)
 * Type: BruteForce
 * 문제 파악
 * 구해야하는 값
 * 일곱난쟁이 리스트
 *
 * 입력
 * 1 <= x <= 100
 *
 * 요구사항
 * - 키의 합이 100이 되는 정렬된 일곱난장이 리스트
 * - 9명중에 7명을 뽑아야 한다.
 *
 * 문제분석
 * 9명중에 7명을 뽑는 것은 9명중에 2명을 제외하는 것이다.
 * 이중 포문을 돌면서 2명을 뽑고 키의 총합에서 두명의 키를 뺀 결과가 100이면 해당 조합을 출력한다.
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val boys = mutableListOf<Int>()
    repeat(9) {
        boys.add(br.readLine().toInt())
    }
    br.close()

    val total = boys.sum()
    outer@ for (i in boys.indices) {
        for (j in i + 1 until boys.size) {
            if (total - boys[i] - boys[j] == 100) {
                boys.removeAt(j)
                boys.removeAt(i)
                break@outer
            }
        }
    }
    boys.sort()
    println(boys.joinToString("\n"))
}
