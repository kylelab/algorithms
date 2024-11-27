package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max


/**
 * Problem : https://www.acmicpc.net/problem/1236
 * TimeComplexity: O(nm) / 행열 탐색을 위한 이중 포문이 최대 시간 / n,m이 최대값이 50이므로 충분히 시간내 풀 수 있다.
 * Type: 행열
 * Solution
 * 경비원이 없는 행과 열을 구한후 최대값을 출력한다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val array: Array<CharArray> = Array(n) {
        br.readLine().toCharArray()
    }
    br.close()

    val (nonIncludedRow, nonIncludedCol) = solution2(array, m, n)
    println(max(nonIncludedRow, nonIncludedCol))
}

private fun solution1(
    array: Array<CharArray>,
    m: Int,
    n: Int
): Pair<Int, Int> {
    var includedRow = 0
    for (row in array) {
        for (i in 0 until m) {
            if (row[i] == 'X') {
                includedRow++
                break
            }
        }
    }
    val nonIncludedRow = n - includedRow

    var includedCol = 0
    for (j in 0 until m) {
        for (i in 0 until n) {
            if (array[i][j] == 'X') {
                includedCol++
                break
            }
        }
    }
    val nonIncludedCol = m - includedCol
    return Pair(nonIncludedRow, nonIncludedCol)
}


private fun solution2(
    array: Array<CharArray>,
    m: Int,
    n: Int
): Pair<Int, Int> {

    // 행/열 별로 경비원이 있는 지 확인하는 불린 배열 만들기
    val includedRow = BooleanArray(n)
    val includedCol = BooleanArray(m)

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (array[i][j] == 'X') {
                includedRow[i] = true
                includedCol[j] = true
            }
        }
    }

    var nonIncludedRowCount = n
    for (i in 0 until n) {
        if (includedRow[i]) {
            nonIncludedRowCount--
        }
    }

    var nonIncludedColCount = m
    for (i in 0 until m) {
        if (includedCol[i]) {
            nonIncludedColCount--
        }
    }

    return Pair(nonIncludedRowCount, nonIncludedColCount)
}