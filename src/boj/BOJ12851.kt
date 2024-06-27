import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/12851
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 숨바꼭질2
 * bfs를 통해 시작점에서 끝점을 이동할때 "한번"에 3포인트씩 이동, 한번에 각각 한 포인트 씩 이동하니까 이 3포인트는 이동하는데 1초라고 생각해야하낟.
 *
 * BFS 알고리즘은 최단 경로를 보장하는 탐색 방법입니다.
 *
 *
 *
 *
 */


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    bfs(n, k)
}

private fun bfs(start: Int, end: Int) {
    val queue = LinkedList<Int>()
    queue.offer(start)

    val count = IntArray(100001)

    val visited = IntArray(100001)
    visited[start] = 1
    count[start] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now == end) {
            break
        }

        val nexts = arrayOf(now - 1, now + 1, now * 2)
        for (next in nexts) {
            if (next !in 0..100000) {
                continue
            }

            if (visited[next] == 0) {
                queue.offer(next)
                count[next] =
                    count[now] // next 정점을 처음 방문하게 되면, next 정점에 도달하는 모든 최단 경로는 현재 정점(now)에서 next로 이동하는 경로를 포함합니다.
                visited[next] =
                    visited[now] + 1
                // 현재 정점(now)에서 인접한 정점(next)으로 이동할 때, next 정점에 도달하는 최단 경로는 now 정점에 도달하는 최단 경로에 1을 더한 값이 됩니다.
                // 각 정점이 방문된 횟수,
            } else if (visited[next] == visited[now] + 1) {
                count[next] += count[now]
            }
        }
    }

    println(visited[end] - 1)
    println(count[end])
}
