import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/9019
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * DSLR
 *
 * 결과 저장용 리스트를 선언한다. -> 각 탐색 경로별로 따로따로 다 저장해야한다.

 * DSLR 연산에 대한 결과값을 하나씩 BFS 탐색을 실시한다.
 *
 * node에 대한 탐색 결과를 저장하려면
 * 해당 node의 실제 값과, 현재 올때까지의 탐색 결과를 저장하는 StringBuilder로 구성된 node 객체로 탐색한다.
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val testCases = br.readLine().toInt()

    val calculators: List<Calculator> = listOf(
        Calculator("D") {
            it * 2 % 10000
        },
        Calculator("S") {
            val value = it - 1
            if (value < 0) {
                9999
            } else {
                value
            }
        },
        Calculator("L") {
            (it % 1000) * 10 + it / 1000
        },
        Calculator("R") {
            (it % 10) * 1000 + it / 10
        }
    )

    repeat(testCases) {
        val (start, end) = br.readLine().split(" ").map { it.toInt() }

        val visited = BooleanArray(10000)

        val queue = LinkedList<Node>()
        queue.offer(Node(start))
        visited[start] = true

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            if (now.num == end) {
                println(now.sb.toString())
                break
            }

            for (i in 0..3) {
                val target = calculators[i].command(now.num)
                if (target in 0..<10000 && !visited[target]) {
                    visited[target] = true
                    val sb = StringBuilder(now.sb).append(calculators[i].commandStr)
                    queue.offer(Node(target, sb))
                }
            }
        }
    }

}

private data class Node(val num: Int, val sb: StringBuilder = StringBuilder())

private class Calculator(val commandStr: String, val command: (Int) -> Int)





