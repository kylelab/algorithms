import java.util.Scanner

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)

    val n = sc.nextInt()
    sc.close()

    var sum = 0L
    for(i in 1..n) {
        sum += (n/i)*i  
        /**
         * 약수의 반대는 배수이고
         * n의 배수는 n을 약수로 갖는 수이다. 
         * 그러므로 n이하의 자연수 중에서 i를 약수로 갖는 수(i의 배수)의 개수는 n/i이다.
         * 그렇기 때문에 n/i에 *i를 하면 n이하의 약수의 합 i의 합을 구할 수 있다.
         * 
         * 
         * i는 약수
         * n/i는 i의 배수의 개수 = i를 약수로 갖는 수
         * 
         */
    }
    println(sum)
}