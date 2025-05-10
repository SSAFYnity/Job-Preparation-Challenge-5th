function solution(n, roads, sources, destination) {
    const way = Array(n + 1).fill().map(() => []);

    roads.forEach(road => {
        way[road[0]].push(road[1])
        way[road[1]].push(road[0])
    })

    // destination에서 출발하는 BFS
    let visited = Array(n + 1).fill().map(() => -1);
    const q = [destination];
    visited[destination] = 0;

    while (q.length) {
        const currentNode = q.shift();
        way[currentNode].forEach(nextNode => {
            if (visited[nextNode] == -1) {
                visited[nextNode] = visited[currentNode] + 1;
                q.push(nextNode);
            }
        })
    }

    for (const source of sources) {
        answer.push(visited[source])
    }

    return answer;
}

