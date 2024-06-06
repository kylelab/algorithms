import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import kotlin.math.max
import maxCnt

fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val n = Integer.parseInt(br.readLine())

    val inputs = Array(n, {Array(n, {" "})})

    var inputIndex = 0
    while(true){
        if(inputIndex == n) {
            br.close()
            break
        }

        val inputString = br.readLine()
        val charArray = inputString.toCharArray()
        var columnIndex = 0
        for(charItem in charArray){
            inputs[inputIndex][columnIndex] = charItem.toString()
            columnIndex++
        }
        inputIndex++
    }
    
    /**
     * 인접한 두칸을 고르기 때문에 n제곱
     * 각 행과 열을 모두 검사해야하니까 n제곱
     * 그래서 시간복잡도는 n의 4제곱이다.
     * 
     * 이중 포문을 사용해서 두칸을 고른다.
     *  가로로 두개씩 끝까지
     *  세로로 두개씩 끝까지
     * 골랐으면 swap
     * 전체를 검사해서 최대갯수를 찾는다. 
     * 다시 원래대로 swap
     * 
     */

     for(i in 0..n-1){
        for(j in 0..n-1) {
            if(j+1 <= n-1){
                swap(inputs, i,j, i, j+1)
                check2(inputs, n)
                swap(inputs, i,j, i, j+1)
            }

            if(i+1 <= n-1){
                swap(inputs, i,j, i+1, j)
                check2(inputs, n)
                swap(inputs, i,j, i+1, j)
            }

        }
    }

    println(maxCnt)
}

fun swap(arrays: Array<Array<String>>, i1:Int,j1:Int, i2:Int, j2:Int) {
    val tempValue = arrays[i1][j1]

    arrays[i1][j1] = arrays[i2][j2]
    arrays[i2][j2] = tempValue

}

var maxCnt = 1
fun check(arrays: Array<Array<String>>, n: Int) {
    for(i in 0..n-1){
        var cnt =1
        for(j in 0..n-2){
            if(arrays[i][j] == arrays[i][j+1]){
                cnt++
            }else{
                cnt = 1
            }
            if(cnt>maxCnt){
                maxCnt = cnt
            }
        }

        
        cnt = 1
        for(j in 0..n-2){
            if(arrays[j][i] == arrays[j+1][i]) {
                cnt++
            }else{
                cnt = 1
            }
            if(cnt>maxCnt){
                maxCnt = cnt
            }
        }
        
    }
}


fun check2(arrays: Array<Array<String>>, n: Int){
    for(i in 0..n-1){
        var cnt = 1
        for(j in 1..n-1){
            if(arrays[i][j] == arrays[i][j-1]){
                cnt++
            }else{
                cnt = 1
            }
            if(cnt>maxCnt){
                maxCnt = cnt
            }
        }

        cnt=1

        for(j in 1..n-1){
            if(arrays[j][i]== arrays[j-1][i]){
                cnt++
            }else{
                cnt =1
            }
            if(cnt>maxCnt){maxCnt = cnt
        }
    }
    }
}
