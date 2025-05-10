import sys
input = sys.stdin.readline

# 특정 원소가 속한 집합 찾기 (x의 루트 노드 찾기)
# 루트 노드를 찾을 때 까지 재귀 호출 (루트 노드는 자신이 부모 노드)
def find_parent(x):
    # 루트 노드가 아니면
    if parent[x] != x:
        # 재귀 호출
        parent[x] = find_parent(parent[x])
    return parent[x]

# 두 원소가 속한 집합 합치기
def union_parent(a, b):
    # 각 루트 노드 찾기
    a = find_parent(a)
    b = find_parent(b)

    # 두 루트가 다르면
    if a != b:
        # a 루트로 일치 시키기
        parent[b] = a
        # a 네트워크 수에 b 네트워크 수 추가
        total[a] += total[b]
    # a, b의 친구 네트워크 수 반환
    return total[a]


t = int(input())
for _ in range(t):
    # 친구 관계 수
    n = int(input())
    # 각 사람의 루트 노드 저장
    parent = dict()
    # 각 사람의 친구 네트워크 수 저장
    total = dict()
    
    for _ in range(n):
        f1, f2 = input().split()
        # 자신의 존재 등록
        if f1 not in parent:
            parent[f1] = f1
            total[f1] = 1
        if f2 not in parent:
            parent[f2] = f2
            total[f2] = 1
        # 루트 노드 찾아서 네트워크 수를 합치고, 반환
        print(union_parent(f1, f2))
