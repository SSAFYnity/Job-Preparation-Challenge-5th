def solution(relation):
    answer_list = []
    N = len(relation[0])
    for i in range(1, 1 << N):
        tmp_set = set()
        for j in range(len(relation)):
            tmp = ''
            for k in range(N):
                if i & (1 << k):
                    tmp += relation[j][k]
            tmp_set.add(tmp)

        if len(tmp_set) == len(relation):
            possible = True
            for num in answer_list:
                if (num & i) == num:
                    possible = False
                    break
            if possible:
                answer_list.append(i)

    return len(answer_list)

relation = [["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]]
solution(relation)