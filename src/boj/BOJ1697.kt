import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/1697
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 숨바꼭질
 *
 * 왜 BFS로 풀어야할까?
 *
 *
 *
 */
private var n: Int = 0
private var k: Int = 0
private val visited: IntArray = IntArray(100001) { 0 }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (nInput, kInput) = br.readLine().split(" ").map { it.toInt() }
    n = nInput
    k = kInput

    val ans = bfs(n, k)
    println(ans)

}

/**
 * X-1
 * X+1
 * 2*X
 *
 * 3개의 점으로 이동하는 bfs를 구현한다.
 * 방문할때마다 단계를 추가한다.
 */
private fun bfs(start: Int, end: Int): Int {
    val queue = LinkedList<Int>()
    queue.offer(start)
    visited[start] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now == end) {
            // 이 곳이 실제로 탐색을 할때이다.
            break
        }

        val nexts = arrayOf(now - 1, now + 1, now * 2)
        for (i in 0..<3) {
            val next = nexts[i]
            if (next in 0..100000 && visited[next] == 0) {
                visited[next] = visited[now] + 1
                queue.offer(next)
            }
        }
    }

    return visited[end] - 1
}
