from itertools import combinations

def solution(relation):
    columns = len(relation[0])
    rows = len(relation)
    
    group = []
    
    for i in range(1,columns + 1):
        combination = combinations(range(columns), i)
        for y_lst in combination:
            check = []
            for x in range(rows):
                lst = []
                for y in y_lst:
                    lst.append(relation[x][y])
                check.append(lst)
            if len(list(set(map(tuple, check)))) == rows:
                group.append(set(y_lst))
    
    group_ = group.copy()
    for i in range(len(group)):
        for j in range(i+1, len(group)):
            key1 = group[i]
            key2 = group[j]
            if key1.issubset(key2):
                try:
                    group_.remove(key2)
                except:
                    continue
    
    return len(group_)