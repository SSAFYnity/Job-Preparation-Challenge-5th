def match(word, ban_word):
    if len(word) != len(ban_word):
        return False
    
    for i in range(len(word)):
        if ban_word[i] != '*' and ban_word[i] != word[i]:
            return False
        
    return True

def solution(user_id, banned_id):
    result = set()
    
    visited = [False] * (len(user_id) + 1)
    
    def dfs(ban_point, db):
        if ban_point == len(banned_id):
            result.add(tuple(sorted(db)))
            return
        
        for idx, user in enumerate(user_id):
            if not visited[idx] and match(user,banned_id[ban_point]):
                visited[idx] = True
                dfs(ban_point+1, db + [idx])
                visited[idx] = False
                
    dfs(0,[])
    
    return len(result)