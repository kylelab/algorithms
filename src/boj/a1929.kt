import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

fun main(args: Array<String>) {
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val st: StringTokenizer = StringTokenizer(br.readLine(), " ")
    val m = Integer.parseInt(st.nextToken())
    val n = Integer.parseInt(st.nextToken())

    /**
     * 에라토스테네스의 체 알고리즘의 핵심 아이디어
     * 알고리즘의 핵심 아이디어는 소수의 배수는 소수가 아니라는 것입니다. 
     * 예를 들어, 2는 소수이므로 2의 배수 (4, 6, 8, ...)는 소수가 아닙니다. 
     * 이와 같은 방식으로 모든 소수의 배수를 제거하면 남은 수는 소수입니다.
     * 
     * index * index <= n 조건은 알고리즘의 효율성을 높이기 위한 것입니다. 
     * index가 sqrt(n)보다 커지면, index의 배수는 이미 이전 단계에서 다른 수의 배수로 처리되었을 가능성이 높습니다. 
     * 즉, index의 제곱이 n을 초과하면, 그 이상의 index에 대해서는 검사할 필요가 없습니다.
     * 
     * 제일 작은 소수부터 최대값까지 돌면서 소수의 배수들을 지워준다.
     * 소수를 저장하는 배열을 만든다. true로 초기화
     * 소수가 이면 true, 소수가 아니면 false
     * checked[1] = false -> 1은 소수가 아니므로
     * i = 2, i*i <= n(max), i++ -> i의 제곱이 max를 넘어 가면 그안의 소수는 모두 구해진거다.
     * j = i+i, j<n(max)까지 돌면서 j = j+i -> i+i는 자기자신빼고 i*2부터고, j+i는 하나씩 올려가며 곱하기다.
     * 
     * checked를 돌면서 true만 출력한다. 
     * 
     */

    if (n < 1) return  
    val prime = BooleanArray(n+1) { true } // 배열은 0부터 시작하고, 소수 계산은 1부터 해야하니까.
    if (n >= 1) prime[1] = false  // n이 1 이상이면 prime[1]을 false로 설정

    var index = 2
    do {
        if(prime[index] == true) {
            var j = index * index // 더 효율적, i*i아래의 값들은 기존에 이미 다 수행됨
            while(j <= n) {
                prime[j] = false
                j = j+index
            }
        }
        index++
    }while(index*index <= n)    // index가 


    for(i in m..n) {
        if(prime[i] == true){
            println(i)
        }
    }
}

