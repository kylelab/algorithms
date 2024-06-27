import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/2178
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 미로 탐색
 *
 * 최대 100*100 크기로 주어지는 미로는 노드(이동할 수 있는 칸)의 개수가 최대 10,000개이고 각 노드가 4개의 방향에 대한 간선을 가지는 그래프로도 볼 수 있다.
 * (1,1)에서 시작해 각 칸에서 갈 수 있는 모든 방향으로 한 칸씩 이동해보는 것을 반복하면 이때 (N,M)에 도달할 수 있는 경로 중 가장 적은 이동을 한 것이 답이 된다.
 * 이 문제를 출발점에서부터 시작해 가능한 모든 경로를 탐색하는 DFS로 접근한다면, 한 번 도착했던 칸도 다른 탐색 경로에서 다시 탐색하게되어 비효율이 발생할 수 있다.
 * 이 문제를 출발점에서부터 시작해 가능한 모든 경로를 탐색하는 BFS로 접근한다면, 같은 칸에 대해서 최초로 탐색되었을 때가 최단 경로이므로 같은 정점을 다시 방문할 필요가 없다.
 *
 * (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 * 탐색하는 한텀을 한칸씩 이동했다고 생각하면 된다.
 *
 */
private var n: Int = 0
private var m: Int = 0
private lateinit var graph: Array<IntArray>
private lateinit var visited: Array<IntArray>

val nearRow = intArrayOf(-1, 1, 0, 0)
val nearCol = intArrayOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (nInput, mInput) = br.readLine().split(" ").map { it.toInt() }
    n = nInput
    m = mInput

    graph = Array(n) {
        br.readLine().map { it.toString().toInt() }.toIntArray()
    }
    br.close()

    visited = Array(n) { IntArray(m) { 0 } }

    val ans = bfs(Point(0, 0), graph, visited, n, m)
    println(ans)
}

private fun bfs(point: Point, graph: Array<IntArray>, visited: Array<IntArray>, n: Int, m: Int): Int {
    val queue = LinkedList<Point>()
    queue.offer(point)
    visited[point.row][point.col] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()

        for (i in 0..<4) {
            val nr = nearRow[i] + now.row
            val nc = nearCol[i] + now.col

            if (!(nr in 0..<n && nc in 0..<m)) continue

            if (visited[nr][nc] == 0 && graph[nr][nc] == 1) {
                visited[nr][nc] = visited[now.row][now.col] + 1
                queue.offer(Point(nr, nc))
            }
        }

    }
    return visited[n - 1][m - 1]
}

private data class Point(val row: Int, val col: Int)

