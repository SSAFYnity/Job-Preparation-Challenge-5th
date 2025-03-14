def solution(files):
    candidates = []
    for file in files:
        head, number, tail = "", "", ""
        flag = False
        for c in file:
            if not number and not c.isdigit():
                head += c
            elif c.isdigit() and len(number) < 5 and not flag:
                number += c
            else:
                tail += c
                flag = True  # number의 길이가 5보다 작을 때 tail을 처리하다가 다시 숫자를 만나는 경우를 방지

        candidates.append((head, number, tail))

    candidates.sort(key=lambda x: (x[0].lower(), int(x[1])))
    return ["".join(candidate) for candidate in candidates]