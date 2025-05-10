def rotate_90(matrix):
    m = len(matrix)
    return [[matrix[m - 1 - j][i] for j in range(m)] for i in range(m)]

def is_unlocked(expanded_lock, key, x, y, m, n):
    temp_lock = [row[:] for row in expanded_lock]  # 확장된 자물쇠의 복사본 생성

    for i in range(m):  # 열쇠를 확장된 자물쇠에 배치
        for j in range(m):
            temp_lock[x + i][y + j] += key[i][j]

    for i in range(n):  # 원래 자물쇠 부분만 확인
        for j in range(n):
            if temp_lock[m - 1 + i][m - 1 + j] != 1:
                return False
    return True

def solution(key, lock):
    m, n = len(key), len(lock)

    expanded_size = n + 2 * (m - 1)  # 확장된 자물쇠 생성 (패딩 포함)
    expanded_lock = [[0] * expanded_size for _ in range(expanded_size)]

    for i in range(n):  # 확장된 자물쇠 중앙에 기존 자물쇠 배치
        for j in range(n):
            expanded_lock[m - 1 + i][m - 1 + j] = lock[i][j]

    for _ in range(4):
        key = rotate_90(key)  # 열쇠 회전

        for x in range(n + m - 1):  # 열쇠를 이동하며 확인
            for y in range(n + m - 1):
                if is_unlocked(expanded_lock, key, x, y, m, n):
                    return True

    return False
