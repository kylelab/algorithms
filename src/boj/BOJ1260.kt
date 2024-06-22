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
    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }
    val input: Array<IntArray> = Array(n + 1) { IntArray(n + 1) { 0 } }

    for (i in 1..m) {
        val (src, dst) = br.readLine().split(" ").map { it.toInt() }
        input[src][dst] = 1
        input[dst][src] = 1
    }

    val visited = BooleanArray(n + 1)

    /**
     * 초기화
     * 방문여부를 확인하기 위한 배열 visited를 초기화
     *
     * 함수 정의
     * DFS 함수는 현재 방문하는 노드와 visited 배열을 매개변수로 받는다.
     * 현재 노드를 방문처리하고, 관련 작업 수행
     *
     * 현재 노드와 인점한 모든 노드에 대해 방문하지 않은 경우 해당노드에 대해 DFS 함수 재귀 호출
     *
     * 재귀 호출의 종료 조건
     * 모든 인접 노드가 방문 된경우, 재귀 호출은 자동으로 종료
     */
    fun dfs(node: Int) {
        visited[node] = true
        print("$node ")
        for (i in 1..n) {
            if (input[node][i] != 1 || visited[i])
                continue
            dfs(i)
        }
    }

    /**
     * 큐 초기화
     * 시작 노드를 Queue넣는다.
     * 시작 노드 방문 여부 체크
     *
     * 큐 처리
     * 큐의 맨암펭서 노드를 하나 꺼낸다.
     * 꺼낸 노드 방문 처리-> 출력등 다른 처리를 할 수 있다.
     * 해당노드를 for문돌면서 노드와 간선이 연결되어 있고, 기존에 방문하지 않는 노드를 모두 큐와 방문여부에 추가한다.
     *
     * 출력은 queue에서 빼낼때한다? 왜지?
     *  정확한 순서보장 ->
     *  중복방지 -> 넣을때가 아닌 꺼낼때 처리하면 같은 노드를 중복해서 처리하는 일을 방지할 수 있다.
     *  노드를 큐에 넣을때는 해당 노드가 이미 큐에 있거나 이미 처리된 상태일 수도 있기때문에,
     *  큐에서 노드를 꺼내 처리함으로써, 실제로 노드를 탐색하고 처리하는 작업을 확실히 수행할 수 있다.
     *
     */
    fun bfs(node: Int) {
        val queue: Queue<Int> = LinkedList()
        queue.add(node)
        visited[node] = true

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            print("$now ")

            for (i in 1..n) {
                if (input[now][i] == 1 && !visited[i]) {
                    queue.add(i)
                    visited[i] = true
                }
            }
        }

    }

    dfs(v)
    println()
    visited.fill(false)
    bfs(v)
}
