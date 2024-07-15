import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/1194
 * TimeComplexity:
 * Algorithim: DFS
 * Solution
 * 순열 사이클
 *
 * 진입차수가 1이고, 진출 차수가 1이면 항상 싸이클이 발생한다.
 * 자기자신으로 돌아온다.
 * 왜냐면 진입/진출이 모두 1이면 1이기때문에 끊기지않고 이어져야하고 그렇게 되면 무조건 자기자신으로 돌아온다.
 * 결국 한노드에서 시작하여 다음 노드로 이동하면 다시 원래의 노드로 돌아올때까지 계속 이동할 수 있다.
 * 진입 진출차수가 모두1이므로 한 노드에서 시작하면 반드시 모든 노드를 거쳐 다시 시작점으로 돌아온다.
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val testCases = br.readLine().toInt()

    repeat(testCases) {
        val n = br.readLine().toInt()
        val inputs = br.readLine().split(" ").map { it.toInt() }.toIntArray()

        val graph = IntArray(inputs.size + 1)
        for (i in inputs.indices) {
            graph[i + 1] = inputs[i]
        }

        val visited = BooleanArray(n + 1)

        var ans = 0
        for (i in 1..n) {
            if (!visited[i]) {
                ans++
                dfs(graph, graph[i], visited)
            }
        }

        println(ans)
    }
    br.close()
}

private fun dfs(graph: IntArray, node: Int, visited: BooleanArray) {
    visited[node] = true

    val next = graph[node]
    if (!visited[next]) {
        dfs(graph, next, visited)
    }
}










