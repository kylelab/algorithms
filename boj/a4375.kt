import java.util.Scanner

fun main(args: Array<String>) {
    val scanner:Scanner = Scanner(System.`in`)

    while(scanner.hasNextInt()){
        val target = scanner.nextInt()
        var num = 0
        for (i in 1..target) {
            num = num*10+1
            num = num%target
            if(num == 0){
                println(i)
                break
            }
        }
    }
}

