class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val n = board.size
        val m = board[0].size
        val pSum = Array(n + 1) { IntArray(m + 1) }
        
        // skill에 따른 차분 배열 업데이트
        for (s in skill) {
            // 공격은 -1, 회복은 +1
            val type = if (s[0] == 1) -1 else 1
            val r1 = s[1]
            val c1 = s[2]
            val r2 = s[3]
            val c2 = s[4]
            val deg = s[5] * type
            
            pSum[r1][c1] += deg
            pSum[r2 + 1][c2 + 1] += deg
            pSum[r1][c2 + 1] -= deg
            pSum[r2 + 1][c1] -= deg
        }
        
        // 1. 세로 누적합 계산
        for (r in 1..n) {
            for (c in 0..m) {
                pSum[r][c] += pSum[r - 1][c]
            }
        }
        
        // 2. 가로 누적합 계산
        for (r in 0..n) {
            for (c in 1..m) {
                pSum[r][c] += pSum[r][c - 1]
            }
        }
        
        // 결과 계산: 최종 체력이 0 초과인 건물 수 세기
        var answer = 0
        for (r in 0 until n) {
            for (c in 0 until m) {
                if (board[r][c] + pSum[r][c] > 0) answer++
            }
        }
        return answer
    }
}
