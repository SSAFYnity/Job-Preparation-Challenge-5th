def solution(places):
    # 거리 2 이하로 앉지 않기 단, 파티션 있으면 가능
    # 응시자 P, 빈 테이블 O, 파티션 X
    # 지키면 1, 안지키면 0
    answer = []
    def check(idx,i,j):
        if (i+1 < 5):
            if places[idx][j][i+1] == "P":
                return False
            elif places[idx][j][i+1] == "O":
                if (i+2 < 5) and places[idx][j][i+2] == "P":
                    return False
                if (j+1 < 5) and places[idx][j+1][i+1] == "P":
                    return False
                if (j-1 >= 0) and places[idx][j-1][i+1] == "P":
                    return False
        if (j+1 < 5):
            if places[idx][j+1][i] == "P":
                return False
            elif places[idx][j+1][i] == "O":
                if (j+2 < 5) and places[idx][j+2][i] == "P":
                    return False
                if (i+1 < 5) and places[idx][j+1][i+1] == "P":
                    return False
                if (i-1 >= 0) and places[idx][j+1][i-1] == "P":
                    return False
        if (j-1 >= 0):
            if places[idx][j-1][i] == "P":
                return False
            elif places[idx][j-1][i] == "O":
                if (j-2 >= 0) and places[idx][j-2][i] == "P":
                    return False
                if (i-1 >= 0) and places[idx][j-1][i-1] == "P":
                    return False
                if (i+1 < 5) and places[idx][j-1][i+1] == "P":
                    return False
        if (i-1 >= 0):
            if places[idx][j][i-1] == "P":
                return False
            elif places[idx][j][i-1] == "O":
                if (i-2 >= 0) and places[idx][j][i-2] == "P":
                    return False
                if (j-1 >= 0) and places[idx][j-1][i-1] == "P":
                    return False
                if (j+1 < 5) and places[idx][j+1][i-1] == "P":
                    return False
        return True
    
    for place_idx in range(len(places)):
        # P있으면 동, 남 방향으로 거리 2안에 P 있는지 확인
        # 있을때, X가 있으면 통과, O이거나 P이면 불
        success = True
        for i in range(5):
            if not success:
                break
            for j in range(5):
                if not success:
                    break
                if places[place_idx][j][i] == "P":
                    success = check(place_idx,i,j)
         
        if (success):
            answer.append(1)
        else:
            answer.append(0)
    
    return answer