package boj

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Problem : https://www.acmicpc.net/problem/10431
 * TimeComplexity:
 * Type: 삽입정렬
 * 문제 파악
 * 구해야하는 값:
 * 학생들이 뒤로 물러난 걸음 수의 총합
 *
 * 입력: 20개의 양의 정수
 *
 * 요구사항
 * 입력으로부터 한명씩 뽑아서 줄의 맨 앞에 세운다.
 * 1. 자기앞에 자기보다 큰 학생이 있다면 그 학생 앞에 세운다.
 * 1-1. 그 두의 모든 학생들은 한발씩 물러선다.
 * 2. 자기보다 키가 큰 학생이 없다면 맨 뒤에 세운다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tests = br.readLine().toInt()
    val arrays = Array(tests) {
        br.readLine().split(" ").drop(1).map { it.toInt() }.toIntArray()
    }
    br.close()

    for (i in 0 until tests) {
        solution1(i, arrays[i])
    }
}

private fun solution1(testCaseIndex: Int, testCase: IntArray) {
    val sorted = IntArray(testCase.size)
    var count = 0
    outer@ for (i in testCase.indices) {
        for (j in 0 until i) {
            if (sorted[j] > testCase[i]) {
                for (t in i - 1 downTo j) {
                    sorted[t + 1] = sorted[t]
                    count++
                }
                sorted[j] = testCase[i]
                continue@outer
            }
        }
        sorted[i] = testCase[i]
    }
    println("${testCaseIndex + 1} $count")
}

/**
 * 내 앞에 나보다 큰 사람 수만큼 뒤로 물러설 거기 때문에 내 앞의 나 보다 큰 사람수 만 구해도 된다.
 */
private fun solution2(testCaseIndex: Int, testCase: IntArray) {
    var count = 0
    for (i in testCase.indices) {
        for (j in 0 until i) {
            if (testCase[j] > testCase[i]) {
                count++
            }
        }
    }
    println("${testCaseIndex + 1} $count")
}