import copy

def rotation(matrix):
    new_matrix = []
    for y in range(len(matrix[0])):
        row = []
        for x in range(len(matrix)-1,-1,-1):
            row.append(matrix[x][y])
        new_matrix.append(row)
        
    return new_matrix

def find(key, max_lock, m, n):
    for x in range(m+n-1):
        for y in range(m+n-1):
            visited = copy.deepcopy(max_lock)
            crush = False
            
            for key_i in range(m):
                for key_j in range(m):
                    if visited[x+key_i][y+key_j] == key[key_i][key_j] == 1:
                        crush =  True
                        break
                    if visited[x+key_i][y+key_j] == 0:
                        visited[x+key_i][y+key_j] = key[key_i][key_j]
                if crush:
                    break
                    
            if crush: continue
            
            is_lock = False
            
            for lock_i in range(m-1, m-1+n):
                for lock_j in range(m-1, m-1+n):
                    if visited[lock_i][lock_j] == 0:
                        is_lock = True
                        break
                if is_lock:
                    break
            if not is_lock:
                return True
            
    return False
            
def solution(key, lock):
    answer = False
    m = len(key)
    n = len(lock)
    
    max_lock = [[0] * (m*2+n-2) for _ in range(m*2+n-2)]
    for i in range(m-1, m-1+n):
        for j in range(m-1,m-1+n):
            max_lock[i][j] = lock[i-(m-1)][j-(m-1)]
            
    if find(key, max_lock, m, n):
        answer = True
    else:
        for _ in range(3):
            key = rotation(key)
            answer = find(key, max_lock, m ,n)
            if answer:
                break
                
    return answer