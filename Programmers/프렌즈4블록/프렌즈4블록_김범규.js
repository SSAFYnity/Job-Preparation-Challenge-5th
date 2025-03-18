function solution(m, n, board) {
    //배열로 변환하기
    board = board.map((x) => x.split(""));
    
    while(true){
        let blocks = [];
        
        for(let i = 0; i < m - 1; i++){
            for(let j = 0; j < n - 1; j++){
                if(board[i][j] && board[i][j] === board[i + 1][j] && board[i][j] === board[i][j + 1] && board[i][j] === board[i + 1][j + 1]){
                    blocks.push([i, j]);
                }
            }
        }
        
        //파괴할 블록이 없으면
        if(blocks.length === 0){
            return [].concat(...board).filter((x) => !x).length;
        }
        
        for(let elements of blocks){
            let [i, j] = elements;    
            board[i][j] = 0;
            board[i + 1][j] = 0;
            board[i][j + 1] = 0;
            board[i + 1][j + 1] = 0;
        }
        
        for(let i = m - 1; i >= 0; i--){
            if(!board[i].some((x) => !x)){
                continue;
            }
            
            for(let j = 0; j < n; j++){
                let zeroCount = [];
                let blockCount = [];
                
                for(let k = m - 1; k >= 0; k--){
                    if(board[k][j] === 0){
                        zeroCount.push(k);
                    }
                    else{
                        blockCount.push(k);
                    }
                }
                
                //세로 줄 중 하나라도 0이 아닌경우
                if(zeroCount.length !== m){
                    for(let zero in zeroCount){
                        for(let block in blockCount){
                            if(zeroCount[zero] > blockCount[block]){
                                board[zeroCount[zero]][j] = board[blockCount[block]][j];
                                board[blockCount[block]][j] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
