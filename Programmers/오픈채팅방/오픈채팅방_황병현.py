def solution(record):
    answer = []
    
    nickname_lst = dict()
    orders = []
    
    for r in record:
        word = r.split()
        if word[0] == 'Enter':
            nickname_lst[word[1]] = word[2]
            orders.append((word[1], 1))
        elif word[0] == 'Leave':
            orders.append((word[1],2))
        else:
            nickname_lst[word[1]] = word[2]
            
    for order in orders:
        nickname = nickname_lst[order[0]]
        if order[1] == 1:
            answer.append(f"{nickname}님이 들어왔습니다.")
        else:
            answer.append(f"{nickname}님이 나갔습니다.")
    
    return answer