import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.max

/**
 * Problem : https://www.acmicpc.net/problem/1941
 * TimeComplexity:
 * Algorithim: 순열, DFS?
 * Solution
 *
 * 25명중 7명을 뽑는다.
 * 가로 세로 인접해있는지 확인한다.
 * 이다솜파 학생이 4명이상은 포함되어야 한다.
 *
 *
 *
 *
 */

private val students: MutableList<Int> = mutableListOf()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    for (i in 0..<5) {
        val studentLine = br.readLine().split("").filter { it.isNotEmpty() }
        studentLine.forEach {
            if (it == "S") {
                students.add(1) // 이다솜파
            } else {
                students.add(0) // 임도연파
            }
        }
    }
    br.close()

    println(nextCombination(0))
}

private val pick: MutableList<Int> = mutableListOf()

// 25명중 7명 뽑기
private fun nextCombination(numberStudent: Int): Int {
    // base case
    if (pick.size == 7) {
        // 7명 뽑은 후에 조건에 맞는 지 확인한다.
        val sStudentCount = pick.count { students[it] == 1 }
        if (sStudentCount < 4) {
            return 0
        }

        checked.fill(false)
        if (dfs(0) == 7) {
            return 1
        }

        return 0
    }

    // 25명까지 갔는데 7명을 못고른 경우
    if (numberStudent >= 25) {
        return 0
    }

    // recursive case

    // 조합의 개수
    var ret = 0

    // numberStudent 번째 학생을 포함하지 않는 경우
    ret += nextCombination(numberStudent + 1)

    // numberStudent 번째 학생을 포함하는 경우
    pick.add(numberStudent)
    ret += nextCombination(numberStudent + 1)
    pick.removeLastOrNull()

    return ret
}

private val checked: BooleanArray = BooleanArray(7) { false }

/**
 * 7명이 모두 친구이면 7을 리턴 할것
 */
private fun dfs(studentNumber: Int): Int {
    checked[studentNumber] = true
    var count = 1
    val me = pick[studentNumber]
    for (i in 1..<7) {
        val friend = pick[i]
        if (!checked[i] && isFriend(me, friend)) {
            count += dfs(i)
        }
    }
    return count
}

private fun isFriend(a: Int, b: Int): Boolean {
    val diff = abs(a - b)
    val max = max(a, b)

    if (diff == 1 && max % 5 != 0) {
        return true
    }

    if (diff == 5) {
        return true
    }

    return false
}
