
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/18290
 * TimeComplexity: O((NM)^k) = 10x10개당 최대 4번 호출된다. -> 1억개라서 너무 크다.
 * Algorithim: 브루트 포스/ 재귀
 * Solution
 * N행 M열의 격자판에서 K개의 칸을 선택하는 문제
 * 선택한 칸에 들어있는 수의 합을 최대로 하는 문제
 *
 * K는 최대 4칸
 * 인접하면 안됨
 *
 * N행 M열을 순회하면 이미 선택했는지 확인하고, 선택하지 않았다면 인접한 칸들이 선택되었는지 검사
 * 순회하면서 하는 선택할지말지는 순서로 풀어야한다?
 *
 * 경우의 수는 100*99*98*97 / 4*3*2*1 = 3,921,225가지수이다.
 *
 * n, m 행렬
 * selected 행렬
 *
 * 재귀 함수
 * 행열 순회한다.
 * 이 칸이 이미 선택되었는지 확인한다.
 * 선택안되었으면 기존 선택칸들과 인접했는지 비교
 * 인접안했으면 선택
 * K개가 될때까지 재귀 돌기
 * 선택했으면 cnt+1
 * 선택했으면 기존 sum과 더하기
 * go(cnt, sum, k)
 *
 * 인접한 칸 계산
 * 4칸의 x,y를 미리 생성해둔다.
 * for문으로 4칸이 이미 사용중인지 본다.
 *
 * 종료 조건
 * 선택한 칸이 K개가 되면 종료
 * 기존 답이랑 sum
 */

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
        val array: Array<IntArray> =
            Array(n) {
                br
                    .readLine()
                    .split(" ")
                    .map { it.toInt() }
                    .toIntArray()
            }
        br.close()

        val nearX = intArrayOf(-1, 1, 0, 0)
        val nearY = intArrayOf(0, 0, -1, 1)

        val selected: Array<BooleanArray> = Array(n) { BooleanArray(m) { false } }

        var ans = -Int.MAX_VALUE

        fun go(
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
                    if (selected[i][j]) continue

                    var canSelected = true
                    for (r in 0 until 4) {
                        val nx = i + nearX[r]
                        val ny = j + nearY[r]
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                            if (selected[nx][ny]) {
                                canSelected = false
                            }
                        }
                    }
                    if (canSelected) {
                        selected[i][j] = true
                        go(cnt + 1, sum + array[i][j])
                        selected[i][j] = false
                    }
                }
            }
        }

        fun goFast(
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
                val y = if (px == i) py else 0
                for (j in y until m) {
                    if (selected[i][j]) continue

                    var canSelected = true
                    for (r in 0 until 4) {
                        val nx = i + nearX[r]
                        val ny = j + nearY[r]
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && selected[nx][ny]) {
                            canSelected = false
                        }
                    }

                    if (canSelected) {
                        selected[i][j] = true
                        goFast(i, j, cnt + 1, sum + array[i][j])
                        selected[i][j] = false
                    }
                }
            }
        }

        goFast(0, 0, 0, 0)
        println(ans)
    }

