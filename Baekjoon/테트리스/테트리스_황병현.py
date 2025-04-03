from sys import stdin

def input():
    return stdin.readline().rstrip()

C, P = map(int,input().split())
board = list(map(int,input().split()))

# flat 4, one : 1 / flat 2 : 2 / flat 2 up, up down : 3 / up flat 2, down up : 4
# flat 3, up down, down up, up down up : 5
# flat 3, flat 2, down up 2, up deep 2 : 6 / flat 3, flat 2, up 2 down, deep 2 up : 7

# 0 : flat 4, 1 : flat 3, 2 : flat 2, 3 : flat 2 up, 4 : up flat 2, 5 : down up,
# 6 : up down, 7 : up down up, 8 : down up 2, 9 : up 2 down, 10 : up deep 2, 11 : deep 2 up
check_lst = [0] * 12

# flat 4
for i in range(3,C):
    if board[i-3] == board[i-2] == board[i-1] == board[i]:
        check_lst[0] += 1

# flat 3
for i in range(2, C):
    # flat 3
    if board[i-2] == board[i-1] == board[i]:
        check_lst[1] += 1
    # flat 2 up
    elif board[i-2] == board[i-1] == board[i]-1:
        check_lst[3] += 1
    # up flat 2
    elif board[i-2] - 1 == board[i-1] == board[i]:
        check_lst[4] += 1
    # up down up
    elif board[i-2] == board[i-1] + 1 == board[i]:
        check_lst[7] += 1
    # down up 2
    elif board[i-2] + 1 == board[i-1] == board[i]:
        check_lst[8] += 1
    # up 2 down
    elif board[i-2] == board[i-1] == board[i] + 1:
        check_lst[9] += 1

# flat 2, 
for i in range(1, C):
    # flat 2
    if board[i-1] == board[i]:
        check_lst[2] += 1
    # up down
    elif board[i-1] - 1 == board[i]:
        check_lst[6] += 1
    # down up
    elif board[i-1] + 1 == board[i]:
        check_lst[5] += 1
    # up deep 2
    elif board[i-1] == board[i] + 2:
        check_lst[10] += 1
    # deep 2 up
    elif board[i-1] + 2 == board[i]:
        check_lst[11] += 1
    
if P == 1:
    print(check_lst[0] + C)
elif P == 2:
    print(check_lst[2])
elif P == 3:
    print(check_lst[3] + check_lst[6])
elif P == 4:
    print(check_lst[4] + check_lst[5])
elif P == 5:
    print(check_lst[1] + check_lst[5] + check_lst[6] + check_lst[7])
elif P == 6:
    print(check_lst[1] + check_lst[2] + check_lst[8] + check_lst[10])
elif P == 7:
    print(check_lst[1] + check_lst[2] + check_lst[9] + check_lst[11])