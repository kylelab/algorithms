import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/1260
 * TimeComplexity:
 * Algorithim: DFS, BFS
 * Solution
 * 데이터 입력은 인접 행렬로 한다.
 *  정점의 개수가 1<= n <= 1000 이므로 n^2이 1,000,000(1MB 정도)이므로 많이 크지 않다.
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val arrays: Array<IntArray> = Array(n + 1) { IntArray(n + 1) { 0 } }

    for (i in 0 until m) {
        val (src, dst) = br.readLine().split(" ").map { it.toInt() }
        arrays[src][dst] = 1
        arrays[dst][src] = 1
    }

    val visited = BooleanArray(n + 1) { false }

    fun dfs(node: Int) {
        visited[node] = true
        // 처리

        for (i in 1..n) {
            if (arrays[node][i] == 1 && !visited[i]) {
                dfs(i)
            }
        }
    }

    fun dfsStack(node: Int) {
        val stack = Stack<Int>()
        stack.push(node)
        visited[node] = true

        while (stack.isNotEmpty()) {
            val now = stack.pop()
            //처리
            for (i in 1..n) {
                if (arrays[now][i] == 1 && !visited[i]) {
                    stack.push(i)
                    visited[i] = true
                }
            }
        }

    }

    fun bfs(node: Int) {
        val queue = LinkedList<Int>()
        queue.offer(node)

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            // 처리

            for (i in 1..n) {
                if (arrays[now][i] == 1 && !visited[i]) {
                    queue.offer(i)
                    visited[i] = true
                }
            }
        }


    }

    var ans = 0
    for (i in 1..n) {
        if (!visited[i]) {
            dfs(i)
//            dfsStack(i)
//            bfs(i)
            ans++
        }
    }

    println(ans)

}
