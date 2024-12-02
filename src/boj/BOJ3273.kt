package boj

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Problem: https://www.acmicpc.net/problem/3273
 * TimeComplexity: O(N)
 * Type: 배열
 * 문제 파악
 * 구해야하는 값
 * 쌍의 개수
 *
 * 입력
 * 1 <= n <= 100000
 * 1 <= ai <= 1000000
 * 1 <= x <= 2000000
 * ai + aj = x (1 <= i < j <= n)->i와 J는 같은 값이 될 수 없음을 유의해야한다.
 *
 * 요구사항
 * 입력으로 받은 수열 중에 두수의 합이 X가 되는 쌍의 개수를 출력한다.
 *
 * 문제분석
 * 모든 쌍을 다 검사하려면 O(N^2) = 10,000,000,000이 되므로 이 방법으로는 불가능하다.
 * 대신 x - a = b를 활용해서 a값은 고정해두고, b값이 이 수열에 있는지 확인만 하면된다.
 * b값이 수열에 있는지 바로 알수만 있다면 이중루프에서 루프하나를 제외시킬 수 있다.
 * a,b를 값이 아닌 배열의 인덱스로 활용하면 바로 접근이 가능하다.
 *
 * 1000000 개의 boolean 배열을 만든다.
 * 순회하면서 x보다 작으면 x-ai 값이 있으면 count++
 * 결과는 count/2 출력
 * 같은 쌍이 두번 씩이 등장하기 때문에 나누기 2를 한다.
 *
 * 1000000 * 4byte = 4000000 byte = 3.81..MB
 * 이므로 메모리제한을 넘지 않는다.
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val numbers = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val x = br.readLine().toInt()
    br.close()

    val exist = BooleanArray(1000001) { false }
    for (i in numbers) {
        exist[i] = true
    }

    var count = 0
    /**
     *  i <= (x - 1) / 2로 수정하면 결과에서 /2를 하지 않아도 된다.
     *  짝수의 경우 ai, aj가 같은 경우의 수도 포함하므로 x-1을 빼준다.
     */
    for (i in exist.indices) {
        if (exist[i] && i < x) {
            val targetIndex = x - i // index로 사용할대는 항상 범위 검사가 필요하다.
            if (targetIndex < exist.size && exist[targetIndex]) {
                count++
            }
        }
    }

    println(count / 2)
}
