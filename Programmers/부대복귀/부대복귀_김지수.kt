class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val edges = Array(n + 1) { mutableListOf<Int>() }
        val distance = IntArray(n + 1) { -1 }
        
        // 그래프 구성
        for (road in roads) {
            val (u, v) = road
            edges[u].add(v)
            edges[v].add(u)
        }
        
        // BFS 초기화: destination에서 시작
        val queue = ArrayDeque<Int>()
        distance[destination] = 0
        queue.add(destination)
        
        // BFS 진행
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            for (neighbor in edges[node]) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[node] + 1
                    queue.add(neighbor)
                }
            }
        }
        
        // 각 source에 대해 결과 반환
        return sources.map { distance[it] }.toIntArray()
    }
}
