import java.io.BufferedReader;
import java.io.InputStreamReader;
import kotlin.collections.single
import kotlin.collections.map
import kotlin.intArrayOf

/**
 * Problem: https://www.acmicpc.net/problem/6064
 * Algorithm: 브루트 포스(건너 뛰며 찾기)
 * TimeComplexity: 
 * Solution
 * if x < M, x' = x + 1 else x' = 1
 * if y < N, y' = y + 1 else y' = 1
 * 
 */
fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val totalTestCases = br.readLine().toInt()
    val testCases: Array<IntArray> = Array(totalTestCases){ _ ->
        val singleTestCase = br.readLine()?.split(" ")?.filter { it.isNotBlank() }?.map { it.toInt() }?.toIntArray()
        singleTestCase ?: intArrayOf()
    }

    testCases.forEach { it -> println(getYears(it)) }
}

/**
 * ex M=5, N=7
1: <1,1> 6: <1,6> 11<1,4> 16<1,2> 21<1,7> 26<1,5> 31<1,3>
2: <2,2> 7: <2,7> 12<2,5> 17<2,3> 22<2,1> 27<2,6> 32<2,4>
3: <3,3> 8: <3,1> 13<3,6> 18<3,4> 23<3,2> 28<3,7> 33<3,5>
4: <4,4> 9: <4,2> 14<4,7> 19<4,5> 24<4,3> 29<4,1> 34<4,6>
5: <5,5> 10:<5,3> 15<5,1> 20<5,6> 25<5,4> 30<5,2> 35<5,7>
 
규칙을 찾아보면  x가 M만큼 증가할 때 마다 x의 값은 동일하고 y의 값만 변한다.
마찬가지로, y가 N만큼 증가할때 y의 값은 동일하고 x의 값만 변한다.
 
나머지연산(%)을 이용해 답을 구할 수 있다.
예를들어, 찾는 숫자가 <4,5> == 19라면
(19%5) = 4 ,  (19%7) == 5를 도출해낼 수 있다.
단, 나머지값이 0이 나오는 것을 방지하기 위해 모든 x와 y의 값에서 1을 빼준다
 
m씩 증가시키면서 
 
(i%N) == y 가 나오는 값을 구하고,  +1을 해주면 된다.
 */
fun getYears(inputArray: IntArray): Int{
    val m = inputArray[0]
    val n = inputArray[1]
    val x = inputArray[2] - 1
    val y = inputArray[3] - 1


    for(i in x until (m * n) step m) {
        if(i % n == y) {
            return i+1
        }
    }

    return -1
}

/**
 * 이 방법은 전체를 다검사하는 방법으로 시간 초과
 * O(MN)으로 1,600,000,000 1억개에 1초정도라면 시간을 초과하게 된다. 
 */
fun getYears2(inputArray: IntArray): Int{
    val m = inputArray[0]
    val n = inputArray[1]
    val x = inputArray[2]
    val y = inputArray[3]

    var tempYears = 0
    var tempX = 0
    var tempY = 0

    while(true) {
        if(tempX == x && tempY == y){
            return tempYears
        }

        if(tempX == m && tempY == n){
            return -1
        }

        if(tempX<m) {
            tempX++
        }else{
            tempX = 1
        }

        if(tempY<n) {
            tempY++
        }else{
            tempY = 1
        }

        tempYears++
    }
}