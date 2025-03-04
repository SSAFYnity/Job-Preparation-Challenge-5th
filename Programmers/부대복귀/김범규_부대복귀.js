function solution(n, roads, sources, destination) {
    let answer = [];
    let adjList = Array.from({ length: n + 1 }, () => []);
    
    roads.forEach((road) => {
        adjList[road[0]].push(road[1]);
        adjList[road[1]].push(road[0]);
    });
    
    let distance = new Array(n + 1).fill(-1);
    //자기 위치는 0이다.
    distance[destination] = 0;
    let queue = [[destination, 0]];
    while(queue.length > 0){
        let [cur, dist] = queue.shift();
        //현재 요소와 연결된 곳 탐방
        for(let next of adjList[cur]){
            if(distance[next] === -1){
                distance[next] = dist + 1;
                queue.push([next, dist + 1]);
            }
        }
    }
    
    sources.forEach((source, idx) => {
        answer[idx] = distance[source];
    });
   
    return answer;
}