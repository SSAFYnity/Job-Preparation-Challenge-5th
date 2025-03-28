def solution(records):
    answer = []
    user = {}
    for record in records:
        arr = record.split()
        if arr[0] == 'Enter':
            if arr[1] in user and user[arr[1]] != arr[2]:
                user[arr[1]] = arr[2]
            elif arr[1] not in user:
                user[arr[1]] = arr[2]
        elif arr[0] == 'Change':
            user[arr[1]] = arr[2]

    for record in records:
        arr = record.split()
        if arr[0] == 'Enter':
            tmp = user[arr[1]] + '님이 들어왔습니다.'
            answer.append(tmp)
        elif arr[0] == 'Leave':
            tmp = user[arr[1]] + '님이 나갔습니다.'
            answer.append(tmp)
    return answer