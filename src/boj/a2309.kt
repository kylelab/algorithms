import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import kotlin.math.max

fun main(args: Array<String>){
    args.toString()
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val inputs = mutableListOf<Int>()

    var inputIndex = 0
    while(true){
        if(inputIndex == 9) break

        val n:Int = Integer.parseInt(br.readLine())
        inputs.add(n)

        inputIndex++
    }

    /**
     * 9명의 난쟁이의 키의 합을 구한다. 
     * 이중 포문을 돌면서 두명의 합을 구해서 총합을 뺀다. 뺀결과가 100이면 나머지를 출력한다. 
     */

     var sum = 0
     for(n in inputs) {
        sum += n
     }

     outer@ for(i in 0..inputs.size - 2){
        for(j in i+1..inputs.size - 1) {
            val target = sum - (inputs[i] + inputs[j])
            if(target == 100) {
                inputs.removeAt(j)  // index로 값을 삭제할때는 앞쪽부터 삭제하면 뒤쪽 element가 당겨져서 의도치 않는 버그가 생길 수 있다. 
                inputs.removeAt(i)
                break@outer
            }
        }
     }

     inputs.sort()
     for(n in inputs) {
        println(n)
     }
}

