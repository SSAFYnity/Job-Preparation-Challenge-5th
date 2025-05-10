const dR = [-1, 1, 0, 0];
const dC = [0, 0, -1, 1];
const ROWS = 5;
const COLS = 5;
let visited;

function solution(places) {
    var answer = [];
    
    for(const place of places){
        let result = true;
        visited = Array.from({length: ROWS}, () => 
            Array.from({length: COLS}, 
                () => false
            )
        );
        for(let i = 0; i < ROWS; i++){
            for(let j = 0; j < COLS; j++){
                if(place[i][j] === "P"){
                    result = isSafe(place, i, j);
                    if(!result)
                        break;
                }
            }   
            if(!result)
                break;
        }
        answer.push(+result);
    }
    
    return answer;
}

function isSafe(place, r, c){
    const que = [];
    visited[r][c] = true;
    que.push([r, c, 0]);
    
    while(que.length > 0){
        const curr = que.shift();
       
        if(curr[2] < 2){
            for(let i = 0; i < 4; i++){
                nr = curr[0] + dR[i];
                nc = curr[1] + dC[i];
                if(isOutOfBound(nr, nc) || visited[nr][nc] || place[nr][nc] === "X")
                    continue;
                
                if(place[nr][nc] === "P")
                    return false;
                
                visited[nr][nc] = true;
                que.push([nr, nc, curr[2]+1]);
            }
        }
    }
    return true;
}

function isOutOfBound(r, c){
    return 0 > r || 0 > c || ROWS <= r || COLS <= c; 
}
