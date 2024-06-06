import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Problem: https://www.acmicpc.net/problem/14500
 * Algorithm: 브루트 포스
 * TimeComplexity: O(MN)
 * Solution
 * 나올 경우의 수를 모두 구한 후 최대 값을 반환한다.
 * - i, j로 이중배열을 돌면서 모두 구한다. 
 * 
 */
fun main(){
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val firstLine = br.readLine().split(" ").filter { it.isNotBlank() }.map { it.toInt() }

    val n = firstLine[0] // 세로 크기
    val m = firstLine[1] // 가로 크기

    val array:Array<IntArray> = Array(n){ IntArray(m)}
    for(i in 0 until n) {
        val line = br.readLine().split(" ").filter{it.isNotBlank()}.map{it.toInt()}
        for(j in 0 until m) {
            array[i][j] = line[j]
        }
    }
    br.close()

    var ans = 0
    for(i in 0 until n){
        for(j in 0 until m) {
            /**
             * 1
             * ◼️◼️◼️◼️
             */
            if(j+3<m) {
                val temp = array[i][j] + array[i][j+1] + array[i][j+2] + array[i][j+3]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 2
             * ◼️
             * ◼️
             * ◼️
             * ◼️
             */
            if(i+3<n) {
                val temp = array[i][j] + array[i+1][j] + array[i+2][j] + array[i+3][j]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 3
             * ◼️◼️
             * ◼️◼️
             */
            if(i+1<n && j+1<m) {
                val temp = array[i][j] + array[i][j+1] + array[i+1][j] + array[i+1][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 4
             * ◼️
             * ◼️
             * ◼️◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j] + array[i+1][j] + array[i+2][j] + array[i+2][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 5
             *  ◼️
             *  ◼️
             * ◼️◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j+1] + array[i+1][j+1] + array[i+2][j+1] + array[i+2][j]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 6
             * ◼️◼️
             * ◼️
             * ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j] + array[i][j+1] + array[i+1][j] + array[i+2][j]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 7
             * ◼️◼️
             *  ◼️
             *  ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j] + array[i][j+1] + array[i+1][j+1] + array[i+2][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 8
             *   ◼️
             * ◼️◼️◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j+2] + array[i+1][j] + array[i+1][j+1] + array[i+1][j+2]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 9
             * ◼️
             * ◼️◼️◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j] + array[i+1][j] + array[i+1][j+1] + array[i+1][j+2]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 10
             * ◼️◼️◼️
             * ◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j] + array[i][j+1] + array[i][j+2] + array[i+1][j]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 11
             * ◼️◼️◼️
             *   ◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j] + array[i][j+1] + array[i][j+2] + array[i+1][j+2]
                if(temp > ans) {
                    ans = temp
                }
            }


            /**
             * 12
             * ◼️
             * ◼️◼️
             *  ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j] + array[i+1][j] + array[i+1][j+1] + array[i+2][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 13
             *  ◼️
             * ◼️◼️
             * ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j+1] + array[i+1][j] + array[i+1][j+1] + array[i+2][j]
                if(temp > ans) {
                    ans = temp
                }
            }
            
            /**
             * 14
             *  ◼️◼️
             * ◼️◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j+1] + array[i][j+2] + array[i+1][j] + array[i+1][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 15
             * ◼️◼️
             *  ◼️◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j] + array[i][j+1] + array[i+1][j+1] + array[i+1][j+2]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 16
             *  ◼️
             * ◼️◼️◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j+1] + array[i+1][j] + array[i+1][j+1] + array[i+1][j+2]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 17
             * ◼️◼️◼️
             *  ◼️
             */
            if(i+1<n && j+2<m) {
                val temp = array[i][j] + array[i][j+1] + array[i][j+2] + array[i+1][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 18
             *  ◼️
             * ◼️◼️
             *  ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j+1] + array[i+1][j] + array[i+1][j+1] + array[i+2][j+1]
                if(temp > ans) {
                    ans = temp
                }
            }

            /**
             * 19
             * ◼️
             * ◼️◼️
             * ◼️
             */
            if(i+2<n && j+1<m) {
                val temp = array[i][j] + array[i+1][j] + array[i+1][j+1] + array[i+2][j]
                if(temp > ans) {
                    ans = temp
                }
            }
        }
    }

    println(ans)
}