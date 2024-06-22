import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem https://www.acmicpc.net/problem/18290
 * TimeComplexity:
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * NxM 행렬이므로 하나씩 돌면서 풀 수 있으나, 1 <= N, M <= 10 이고, (NM)4승 이므로 1억으로 1초가 넘어가게 된다.
 * 행렬로 풀되 0부터 돌게 되면 너무 많이 돌게 되므로 현재부터 다시 도는 것으로 한다.
 */
private var n: Int = 0 // 행
private var m: Int = 0 // 열
private var k: Int = 0 // 선택할 갯수
private var array: Array<IntArray> = emptyArray()

private val c =
    Array<BooleanArray>(10) {
        BooleanArray(10) { false }
    }

private val nearX = arrayOf(0, 0, 1, -1)
private val nearY = arrayOf(1, -1, 0, 0)

private var ans: Int = -Int.MAX_VALUE

fun main() {
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val inputs = br.readLine()?.split(" ")?.filter { it.isNotBlank() }
    if ((inputs?.size ?: 0) < 3) {
        return
    }
    n = inputs?.get(0)?.toInt() ?: 0
    m = inputs?.get(1)?.toInt() ?: 0
    k = inputs?.get(2)?.toInt() ?: 0

    array =
        Array(n) {
            br.readLine()?.split(" ")?.filter { it.isNotBlank() }?.map { it.toInt() }?.toIntArray() ?: intArrayOf()
        }

    br.close()
    goFast(0, 0, 0, 0)
    println(ans)
}

private fun go(
    cnt: Int,
    sum: Int,
) {
    if (cnt == k) {
        if (ans < sum) {
            ans = sum
        }
        return
    }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (c[i][j]) continue

            var canSelected = true
            for (r in 0 until 4) {
                val ni = i + nearX[r]
                val nj = j + nearY[r]

                if (ni > -1 && ni < n && nj > -1 && nj < m && c[ni][nj]) {
                    canSelected = false
                }
            }
            if (canSelected) {
                c[i][j] = true
                val sum = sum + array[i][j]
                go(cnt + 1, sum)
                c[i][j] = false
            }
        }
    }
}

private fun goFast(
    px: Int,
    py: Int,
    cnt: Int,
    sum: Int,
) {
    if (cnt == k) {
        if (ans < sum) {
            ans = sum
        }
        return
    }
    for (i in px until n) {
        val y = if (i == px) py else 0
        for (j in y until m) {
            if (c[i][j]) continue

            var canSelected = true
            for (r in 0 until 4) {
                val ni = i + nearX[r]
                val nj = j + nearY[r]

                if (ni > -1 && ni < n && nj > -1 && nj < m && c[ni][nj]) {
                    canSelected = false
                }
            }
            if (canSelected) {
                c[i][j] = true
                val sum = sum + array[i][j]
                goFast(i, j, cnt + 1, sum)
                c[i][j] = false
            }
        }
    }
}
