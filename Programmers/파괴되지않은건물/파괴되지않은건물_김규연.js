function solution(board, skill) {
    const arr = Array.from({ length: board.length + 1 }, () => Array.from({ length: board[0].length + 1 }, () => 0));

    for (let [type, r1, c1, r2, c2, deg] of skill) {
        const t = type === 1 ? -1 : 1;
        arr[r1][c1] += (t * deg);
        arr[r2 + 1][c2 + 1] += (t * deg);
        arr[r1][c2 + 1] -= (t * deg);
        arr[r2 + 1][c1] -= (t * deg);
    }

    for (let i = 0; i < arr.length; i++) {
        for (let j = 1; j < arr[0].length; j++) {
            arr[i][j] += arr[i][j - 1];
        }
    }
    for (let i = 0; i < arr[0].length; i++) {
        for (let j = 1; j < arr.length; j++) {
            arr[j][i] += arr[j - 1][i];
        }
    }

    return board.reduce((acc, cur, idx) => acc + cur.filter((e, i) => e + arr[idx][i] > 0).length, 0);
}
