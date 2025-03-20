def solution(record):
    info = [i.split() for i in record]
    uid_table = {i[1]: i[2] for i in info if len(i) == 3}  # 최신 변경 정보 반영, 고유 유저 관리

    return [f'{uid_table[i[1]]}님이 들어왔습니다.' if i[0] == 'Enter' else
            f'{uid_table[i[1]]}님이 나갔습니다.'
            for i in info if i[0] != 'Change']