import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val inputArray = br.readLine().split(" ")
    br.close()
    if(inputArray.size != 3){
        return
    }

    val e = Integer.parseInt(inputArray[0])
    val s = Integer.parseInt(inputArray[1])
    val m = Integer.parseInt(inputArray[2])
    val maxYear = 15 * 28 * 19

    var ansYear =0
    var tempE = 0
    var tempS = 0
    var tempM = 0
    while(ansYear <= maxYear){
        ansYear++
        tempE++
        tempS++
        tempM++
        if(tempE > 15) { 
            tempE = 1
        }
        if(tempS > 28) { 
            tempS = 1
        }
        if(tempM > 19) { 
            tempM = 1
        }

        if(tempE == e && tempS == s && tempM == m) {
            println(ansYear)
            break
        }
    }
}
