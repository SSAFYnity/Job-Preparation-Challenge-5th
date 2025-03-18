let prefix;
let R;
let C;

function solution(board, skill) {
    R = board.length;
    C = board[0].length;
    
    prefix = Array.from(
        {length: R}, 
        () => Array.from({length: C}, () => 0)
    );
    
    for (const s of skill){
        activateSkill(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
    }
    
    
    for(let r = 0; r < R; r++){
        for(let c = 1; c < C; c++){
            prefix[r][c] += prefix[r][c-1];
        }
    }
    
    for(let r = 1; r < R; r++){
        for(let c = 0; c < C; c++){
            prefix[r][c] += prefix[r-1][c];
        }
    }
    
    return board.reduce((rSum, cur, r) => 
        rSum + board[r].reduce((cSum, cur, c) => 
                               cSum + (cur + prefix[r][c] > 0 ? 1: 0), 
                               0)
    , 0);
}

function activateSkill(type, r1, c1, r2, c2, degree){
    if(type == 1) degree *= -1;
    r2++;
    c2++;
    prefix[r1][c1] += degree;
    
    if(r2 < R)
        prefix[r2][c1] += -degree;
    if(c2 < C)
        prefix[r1][c2] += -degree;
    if(r2 < R && c2 < C)
        prefix[r2][c2] += degree;
    
}
