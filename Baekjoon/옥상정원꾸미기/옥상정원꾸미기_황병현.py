from sys import stdin

def input():
    return stdin.readline().rstrip()

N = int(input())
result = 0
building = []
for _ in range(N):
    h = int(input())
    while building and building[-1] <= h:
        building.pop()
    building.append(h)
    result += len(building) - 1

print(result)