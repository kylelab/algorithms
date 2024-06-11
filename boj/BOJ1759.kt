
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem https://www.acmicpc.net/problem/1759
 * TimeComplexity: O(2c제곱) / c갯수의 알파벳별로 o x로 두개를 선택하는 것이니까
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * 브루트 포스 문제는 방법의 개수가 최대 몇개인지 계산해봐야한다.
 *
 * C의 제한이 15니까 2의 15승이니까 32768이어서 다만들어봐도 된다.
 *
 * 문제 조건
 * 1. 암호의 길이는 L
 * 2. 암호는 소문자
 * 3. 암호는 최소 한개 모음
 * 4. 최소 두개의 자음
 * 5. 증가하는 순서로 배열되어야한다. -> 미리 오름차순으로 정렬해둔다.
 * 6. C가지 선택
 * 7. 서로 다른 L개 중복되면 안된다.
 *
 * index: 몇번째 알파벳인지
 * 알파벳을 사용한다, 안한다.
 * password : 현재까지 만든 만든 암호
 *
 * 다음 경우 호출을 어떻게 만들수 있는가?
 * 알파벳을 사용한다, 안한다.
 * 1. 알파벳을 사용한다.
 *  alpha[i]
 * 결정했으면 i+1
 * passwaord = password + a[i]
 * go(n, alpha, password+alpha[i], i+1)
 *
 * 2. 알파벳을 사용하지 않음
 * i -> i+1
 * go(n, alpha, password, i+1)
 *
 * 완성된 애들중에 한개이상의 모음과 두개 이상의 자음이 포함되어야한다.
 * 포함된 모음 수 > 1, n-포함된 모음 수>2 조건을 충족한 애들만 출력
 *
 * 입력받고
 * 정렬을 하고
 * go(cnt)
 *
 */

var alphabets: Array<Char> = emptyArray()

fun main() {
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val inputs = br.readLine()?.split(" ") ?: emptyList()
    if (inputs.size < 2) {
        return
    }
    val l = inputs[0].toInt() // 암호 갯수
    val c = inputs[1].toInt() // 알파벳 갯수

    alphabets =
        br.readLine()?.split(" ")
            ?.flatMap { it.toList() }
            ?.toTypedArray()
            ?: emptyArray()
    br.close()

    alphabets.sort()

    go(l, "", 0)
}

val moum: Set<Char> = setOf('a', 'e', 'i', 'o', 'u')

fun go(
    l: Int,
    passwaord: String,
    i: Int,
) {
    if (passwaord.length == l) {
        val includeMoumCount = passwaord.toCharArray().count { it in moum }
        if (includeMoumCount > 0 && l - includeMoumCount >= 2) {
            println(passwaord)
        }
        return
    }
    // 왜 언제는 선택했는지 안했는지 표시하고 이거는 선택했을때랑 안했을때로 나누는 거지..

    if (i >= alphabets.size) return

    go(l, passwaord + alphabets[i], i + 1)
    go(l, passwaord, i + 1)
}
