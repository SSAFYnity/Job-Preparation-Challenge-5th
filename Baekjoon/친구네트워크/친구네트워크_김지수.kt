import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val t = br.readLine().toInt()  // 전체 테스트케이스 수

    repeat(t) {
        val f = br.readLine().toInt()  // 친구 관계의 개수
        val friendNums = mutableMapOf<String, Int>()
        val parent = mutableListOf<Int>()
        val networkSize = mutableListOf<Int>()

        // 경로 압축을 사용하는 find 함수
        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        // union by size를 적용한 union 함수
        fun union(x: Int, y: Int): Int {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX == rootY) return networkSize[rootX]

            // 두 트리의 크기를 비교하여 작은 트리를 큰 트리 밑에 붙임
            if (networkSize[rootX] < networkSize[rootY]) {
                parent[rootX] = rootY
                networkSize[rootY] += networkSize[rootX]
                return networkSize[rootY]
            } else {
                parent[rootY] = rootX
                networkSize[rootX] += networkSize[rootY]
                return networkSize[rootX]
            }
        }

        repeat(f) {
            val friends = br.readLine().split(" ")
            val indices = IntArray(2)
            for (i in 0 until 2) {
                val friend = friends[i]
                if (friend !in friendNums) {
                    val idx = friendNums.size
                    friendNums[friend] = idx
                    parent.add(idx)       // 초기 부모는 자기 자신
                    networkSize.add(1)      // 초기 네트워크 크기는 1
                }
                indices[i] = friendNums[friend]!!
            }
            // union 결과 (친구 네트워크의 크기)를 결과에 추가
            sb.append(union(indices[0], indices[1])).append("\n")
        }
    }
    print(sb)
}
