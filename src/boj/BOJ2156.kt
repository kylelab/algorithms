import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * Problem : https://www.acmicpc.net/problem/2156
 * TimeComplexity:
 * Algorithim: 재귀/ 동적계획법
 * Solution
 * 포도주 시식
 *
 * 문제를 쪼개서 생각한다.
 * 각 자리에 있는 포도주를 마실지/안마실지로 구분해서 재귀로 정답을 구한다.
 * 필요한 파라미터는 3개
 * n -> 총 포도주 갯수
 * index -> 현재 선택할 포도주 위치
 * sum -> 마신 포도주의 합
 *
 * 점화식 생각
 * 문제의 요구사항(답): 최대 포도주의 양
 * d[n]? = n 번째 잔까지 선택한 최대 포도주의 양
 * 3가지 케이스 중 제일 큰 값을 선택하면 된다.
 * 1. 기존 최대 와인량 + 마지막 두잔 선택
 * 2. 기존 최대 와인량 + 두번째잔선택안하고, 마지막 한잔 선택
 * 3. 기존 최대 와인량 + 마지막 선택 안함
 *
 * 1. d[i] = d[i-3] + w[i-1] + w[i]
 * 2. d[i] = d[i-2] + w[i]
 * 3. d[i] = d[i-1]
 *
 * n이 1잔일때는 1잔 마시는게 최대 와인량
 * n이 2잔일때는 2잔 다마시는게 최대 와인량이다.
 * 위의 케이스는 3잔부터 적용된다.
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val wines = IntArray(n)
    repeat(n) { index ->
        wines[index] = br.readLine().toInt()
    }

//    chooseWine(n, 0, 0, BooleanArray(n), wines)
//    println(total)
    println(maxWine(wines, n))
}

private var total = 0

private fun chooseWine(n: Int, index: Int, sum: Int, chosen: BooleanArray, wines: IntArray) {
    if (index == n) {
        if (total < sum) {
            total = sum
        }
        return
    }
    // Choose
    if (!isPreChosen(index, chosen)) {
        chosen[index] = true
        chooseWine(n, index + 1, sum + wines[index], chosen, wines)
    }
    // Not choose
    chooseWine(n, index + 1, sum, chosen, wines)
}


private fun isPreChosen(currentIndex: Int, chosen: BooleanArray): Boolean {
    if (currentIndex < 2) {
        return false
    }

    return chosen[currentIndex - 1] && chosen[currentIndex - 2]
}


private fun maxWine(wines: IntArray, n: Int): Int {
    if (n == 0) {
        return 0
    }

    val maxWines = IntArray(n)
    maxWines[0] = wines[0]
    if (n == 1) {
        return maxWines[0]
    }

    maxWines[1] = wines[0] + wines[1]
    if (n == 2) {
        return maxWines[1]
    }

    for (i in 2..<n) {
        maxWines[i] = maxWines.getOrElse(i - 3) { 0 } + wines[i - 1] + wines[i]
        maxWines[i] = max(maxWines[i], maxWines[i - 2] + wines[i])
        maxWines[i] = max(maxWines[i], maxWines[i - 1])
    }

    return maxWines[n - 1]
}


