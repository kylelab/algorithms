package boj

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Problem: https://www.acmicpc.net/problem/10989
 * TimeComplexity:
 * Type: 정렬
 * 문제 파악
 * 구해야하는 값:
 * 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 *
 * 입력: 수의 개수 N(1 <= N <= 10,000,000), 둘째줄부터는 N개의 수(10000이하 자연수)
 *
 * 요구사항
 * 입력으로 받은 수를 정렬하여 출력한다.
 *
 * 문제분석
 * 간단한 삽입정렬로는 시간초과때문에 풀 수 없다.
 * 삽입정렬의 시간복잡도는 O(N^2)이기때문에 100,000,000,000,000가 되어서 시간제한인 5초를 초과하게 된다.
 *
 * N개의 수가 10000이하의 자연수이기 때문에 10001개의 배열을 만들어서 넣고, 해당 배열에 담긴 수만큼 출력한다.
 * 이 풀이가 가능한 이유는 10000이하기 때문에 가능하다.
 * 1,000,000,000개였다면 4,000MB가 필요하기때문에 이 풀이는 불가능하다.
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val numbers = IntArray(n) {
        br.readLine().toInt()
    }
    br.close()

    solution(numbers)
}

private fun solution(numbers: IntArray) {
    val countArray = IntArray(10001)
    for (number in numbers) {
        countArray[number]++
    }

    val sb = StringBuilder()
    for (i in 1..10000) {
        repeat(countArray[i]) {
            sb.append("$i\n")
        }
    }

    println(sb.toString())
}

private fun solutionOvertime(numbers: IntArray) {
    val sortedArray = IntArray(numbers.size)
    outer@ for (i in numbers.indices) {
        for (j in 0..<i) {
            if (sortedArray[j] > numbers[i]) {
                for (t in i - 1 downTo j) {
                    sortedArray[t + 1] = sortedArray[t]
                }
                sortedArray[j] = numbers[i]
                continue@outer
            }
        }
        sortedArray[i] = numbers[i]
    }

    val sb = StringBuilder()
    for (number in sortedArray) {
        sb.append("$number\n")
    }
    println(sb.toString())
}
