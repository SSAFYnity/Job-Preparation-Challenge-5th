import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class GreedyPandaSolution {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var gridSize = 0
    private var longestPath = 0
    private lateinit var forest: Array<IntArray>
    private lateinit var maxPathMemo: Array<IntArray> // 각 좌표에서 시작하는 최장 경로 저장
    private val dx = arrayOf(1, -1, 0, 0)
    private val dy = arrayOf(0, 0, 1, -1)
    
    init {
        readInput()
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                longestPath = max(longestPath, findLongestPath(i, j))
            }
        }
        println(longestPath)
    }
    
    private fun readInput() {
        gridSize = br.readLine().toInt()
        forest = Array(gridSize) { IntArray(gridSize) }
        maxPathMemo = Array(gridSize) { IntArray(gridSize) { 0 } } // 0이면 아직 계산되지 않음
        for (i in 0 until gridSize) {
            forest[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
    }
    
    private fun findLongestPath(row: Int, col: Int): Int {
        // 이미 계산된 경우 반환
        if (maxPathMemo[row][col] != 0) return maxPathMemo[row][col]
        
        maxPathMemo[row][col] = 1 // 기본 경로: 자기 자신만 포함
        
        for (dir in 0 until 4) {
            val nextRow = row + dx[dir]
            val nextCol = col + dy[dir]
            if (nextRow !in 0 until gridSize || nextCol !in 0 until gridSize) continue
            if (forest[row][col] >= forest[nextRow][nextCol]) continue
            maxPathMemo[row][col] = max(maxPathMemo[row][col], findLongestPath(nextRow, nextCol) + 1)
        }
        return maxPathMemo[row][col]
    }
}

fun main() {
    GreedyPandaSolution()
}
