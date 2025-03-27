def solution(user_id, banned_id):
    
    # 불량 사용자가 될 수 있는 지 확인
    def is_banned_user(a, b):
        # a: 불량사용자
        # b: 불량사용자 후보
        k = len(a)
        for i in range(k):
            # "*"이 아니고, 두 문자가 같지 않으면 False 반환
            if a[i] != "*" and a[i] != b[i]:
                return False
        return True
    
    # 불량 사용자 후보 가져오기
    def find_banned_candidates(banned_users, users):
        # 불량 사용자 후보 저장
        candidates = dict()
        for banned_user in banned_users:
            x = len(banned_user)
            candidates[banned_user] = set()
            for candidate in users:
                y = len(candidate)
                # 아이디 길이가 같고 불량 사용자 후보이면
                if x == y and is_banned_user(banned_user, candidate):
                    # 불량 후보로 저장
                    candidates[banned_user].add(candidate)
        return candidates
    
    # 제재 아이디 목록 찾기
    def dfs(banned_idx):
        if banned_idx == n:
            answer.add(tuple(sorted(visited)))
            return
        # 불량 사용자 후보 탐색
        for user in banned_candidates[banned_id[banned_idx]]:
            if user not in visited:
                visited.add(user)
                dfs(banned_idx+1)
                visited.remove(user)

    # 제재 아이디 개수
    n = len(banned_id)
    # 제재 아이디 목록 저장
    answer = set()
    banned_candidates = find_banned_candidates(banned_id, user_id)
    # 현재 탐색에서 선정된 제재 아이디 방문 체크
    visited = set()
    dfs(0)
    
    return len(answer)
