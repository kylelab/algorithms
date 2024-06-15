import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Problem: https://www.acmicpc.net/problem/1107
 * Algorithm: 브루트 포스
 * TimeComplexity: 
 * Solution
 * 나올 경우의 수를 모두 구한 후 최소 값을 비교한다. 
 * 1. 타겟 채널과 현재 채널이 같은 경우
 * 2. 숫자로만 이동이 가능한 경우(타겟 채널에 고장난 버튼이 없는 경우
 * 3. +-로만 이동한 경우
 * 4. 고장나지 않은 버튼으로 갈 수 있는 최소값에서 +로 간경우
 * 5. 고장나지 않은 버튼으로 갈 수 있는 최대값에서 -로 간경우
 * 
 * TODO: 전체 순회로 한번에 푼 사람들 코드 확인해보기
 * 
 */
fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val n = Integer.parseInt(br.readLine())

    val brokenButtonCount = Integer.parseInt(br.readLine())
    val brokenButtons = if(brokenButtonCount>0){
        br.readLine().split(" ").filter { it.isNotBlank() }.map { it.toInt() }
    } else {
        emptyList()
    }
    br.close()

    val hasBronkenButttons = containBrokenButtons(n, brokenButtons)

    // 1. 타겟 채널과 현재 채널이 같은 경우
    if(n == INITIAL_CHANNEL){
        println(0)
        return
    }

    // 2. 숫자로만 이동이 가능한 경우의 최소값
    val count1 = if(!hasBronkenButttons){
        getDigitCount(n)
    } else {
        Int.MAX_VALUE
    }

    // 3. +-로만 이동한 경우
    val m = n - INITIAL_CHANNEL
    val count2 = if(m<0){
        -m
    }else{
        m
    }

    // 4. 고장나지 않은 버튼으로 갈 수 있는 최소값에서 +로 간경우
    // n에서 고장난 버튼이 있는 지 확인하면서 -1하면서 찾기, 찾으면 그수의 자릿수 + (n - 찾은 수 )
    val count3 = if(hasBronkenButttons) {
        var ans:Int? = null
        for(i in n downTo(0)){
            if(!containBrokenButtons(i, brokenButtons)){
                ans = getDigitCount(i) + (n-i)
                break
            }
        }
        ans ?: Int.MAX_VALUE
    } else{
        Int.MAX_VALUE
    }


    // 5. 고장나지 않은 버튼으로 갈 수 있는 최대값에서 -로 간경우
    // n에서 고장난 버튼이 있는 지 확인하면서 +1하면서 찾기, 찾으면 그수의 자릿수 + (n - 찾은 수 )
    val count4 = if(hasBronkenButttons) {
        var ans:Int? = null
        for(i in n..1_000_000){
            if(!containBrokenButtons(i, brokenButtons)){
                ans = getDigitCount(i) + (i-n)
                break
            }
        }
        ans ?: Int.MAX_VALUE
    } else{
        Int.MAX_VALUE
    }

    println(minOf(count1, count2, count3, count4))
}


private const val INITIAL_CHANNEL = 100

fun containBrokenButtons(number: Int, brokenNumbers: List<Int>):Boolean {
    for(brokenNumber in brokenNumbers){
        if(containDigit(number, brokenNumber)){
            return true
        }
    }
    return false
}

fun containDigit(number: Int, digit: Int) :Boolean {
    if(number == 0 && digit == 0){
        return true
    }
    var temp = number
    while(temp>0){
        if(temp %10 == digit){
            return true
        }

        temp /= 10
    }
    return false
}

fun getDigitCount(number:Int): Int{ 
    if(number == 0){
        return 1
    }

    var temp = number
    var digitCount = 0
    while(temp>0){
        temp /= 10
        digitCount++
    }
    return digitCount
}