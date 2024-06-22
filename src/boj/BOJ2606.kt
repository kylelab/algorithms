import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/2606
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
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = Array(n + 1) { IntArray(n + 1) { 0 } }
    for (i in 0 until m) {
        val (src, dst) = br.readLine().split(" ").map { it.toInt() }
        graph[src][dst] = 1
        graph[dst][src] = 1
    }

    val visited = BooleanArray(n + 1) { false }

    var ans = 0
    fun dfsStack(node: Int) {
        val stack = Stack<Int>()
        stack.push(node)
        visited[node] = true    // 이게 없어도 동작하나? 스택은 넣을때 해야하나?

        while (stack.isNotEmpty()) {
            val now = stack.pop()
            ans++
            for (i in 1..n) {
                if (graph[now][i] == 1 && !visited[i]) {
                    stack.push(i)
                    visited[i] = true
                }
            }
        }
    }

    fun dfs(node: Int) {
        visited[node] = true
        ans++

        for (i in 1..n) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfs(i)
            }
        }
    }

    fun bfs(node: Int) {
        val queue = LinkedList<Int>()
        queue.offer(node)
        visited[node] = true

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            ans++

            for (i in 1..n) {
                if (graph[now][i] == 1 && !visited[i]) {
                    queue.offer(i)
                    visited[i] = true
                }
            }
        }
    }

//    dfsStack(1)
//    dfs(1)
    bfs(1)
    println(ans - 1)
}
