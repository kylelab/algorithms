import java.util.Scanner

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)

    val n = sc.nextInt()

    var primeCount = 0
    for(i in 1 .. n){
        val x = sc.nextInt()
        if(isPrime(x)) {
            primeCount++
        }
    }
    sc.close()

    println(primeCount)
}

fun isPrime(x: Int): Boolean {
    // 소수는 1과 자기자신만 약수인 수이다. 
    // 약수는 루트 n을 기점으로 대칭을 이루기 때문에, 제일 작은 소수인 2부터 루트 n까지만 검사하면 된다.
    // 2부터 루트n은 i*i<=n과 같다.
    if(x <= 1) {
        return false
    } else if(x == 2) {
        return true
    }
 
    var i = 2
    do {
        if(x%i == 0) return false   // 2부터 루트n까지 i로 나눠서 나머지가 한번이라도 0이 나오면 1외의 다른 약수가 있기 때문에 소수가 아니다.
        i++
    } while(i * i <= x)
    return true
}

