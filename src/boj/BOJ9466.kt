import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/9466
 * TimeComplexity:
 * Algorithim: DFS
 * Solution
 * 텀 프로젝트
 * 어느 팀에도 속하지 않는 학생들의 수를 계산하는 프로그램을 작성하라
 * 싸이클을 이루지 못한 학생들의 수를 세라
 *
 * 노드의 수가 2<=N<=100,000 -> 모든 노드에서 DFS를 수행하면 O(N^2)으로 시간초과
 * 한번의 DFS로 구할 수 있어야 한다.
 *
 *
 * 팀을 이룬다는 것은 사이클이 만들어져야 한다.
 * 싸이클을 이루고 있는지 어떻게 알지?
 * 매 탐색마다 노드의 depth를 저장하는데 다음에 갈 노드의 depth가 이미 채워져 있다면 싸이클을 이루고 있다고 판단한다.
 * 여기서 싸이클 노드의 개수 = depth[now]-depth[next]+1
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    repeat(tc) {
        val n = br.readLine().toInt()
        val inputs = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        val graph = IntArray(n + 1)
        for (i in 0..<n) {
            graph[i + 1] = inputs[i]
        }

        val depth = IntArray(n + 1)
        var cnt = 0
        for (i in 1..n) {
            if (depth[i] == 0) {
                depth[i] = 1
                cnt += dfs(graph, i, depth)
            }
        }
        // cnt는 싸이클이 있는 노드의 갯수이므로 전체 노드 개수 - 싸이클있는 노드 개수가 정답이 된다.
        println(n - cnt)
    }
}

private fun dfs(graph: IntArray, node: Int, depth: IntArray): Int {
    val nextNode = graph[node]

    var cycleCount = 0
    if (depth[nextNode] == 0) {
        depth[nextNode] = depth[node] + 1
        cycleCount = dfs(graph, nextNode, depth)
    } else {
        cycleCount = depth[node] - depth[nextNode] + 1
    }

    // 다음 탐색을 위해 재귀 안에서 초기화
    depth[node] = 100001
    return if (cycleCount < 0) {
        0
    } else {
        cycleCount
    }
}










