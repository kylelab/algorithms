package boj

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Problem: https://www.acmicpc.net/problem/10448
 * TimeComplexity: O(n^3)
 * Type: BruteForce
 * 문제 파악
 * 구해야하는 값
 * K가 3개의 삼각수의 합으로 표현 될 수 있으면 1, 그렇지 않으면 0 출력
 *
 * 입력
 * 3 <= K <= 1000
 *
 * 요구사항
 * 삼각수를 구하는 공식은 주어짐
 * 주어진 자연수가 3개의 삼각수의 합으로 구성될 수 있는지 검사하라
 *
 * 문제분석
 * K가 최대 1000까지이므로 1000까지되는 삼각수를 리스트로 만든다.
 * 1000까지 유레카 숫자가 가능한지 Boolean 배열을 만들어서 전처리한다.
 * Boolean 배열에서 T값을 랜덤액세스로 확인한다.
 * O(triangleNumberList.size + triangleNumberList.size^3 + T.size) = O(triangleNumberList.size^3)
 *
 * 최적화 고민
 * 3중 포문을 2중 포문으로 줄인다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val kArray = IntArray(t) {
        br.readLine().toInt()
    }
    br.close()

    // 삼각수를 미리 구한다.
    val triangleNumberList = mutableListOf<Int>()
    var triangleNumber: Int
    var index = 1
    while (true) {
        triangleNumber = index * (index + 1) / 2
        if (triangleNumber > 1000) break
        triangleNumberList.add(triangleNumber)
        index++
    }

    // 3개의 합으로 구성이 가능한지 미리 구해둔다.
    val isEurekaNumber = BooleanArray(1001)
    for (i in triangleNumberList) {
        for (j in triangleNumberList) {
            for (k in triangleNumberList) {
                val sum = i + j + k
                if (sum <= 1000) isEurekaNumber[sum] = true
            }
        }
    }

    for (k in kArray) {
        if (isEurekaNumber[k]) {
            println(1)
        } else {
            println(0)
        }
    }
}
