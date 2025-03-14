def solution(files):
    answer = []
    head, number, tail = '', '', ''

    for file in files:
        for i in range(len(file)):
            if file[i].isdigit():  # 숫자가 나오면 그 이전은 무조건 HEAD
                head = file[:i]
                number = file[i:]

                for j in range(len(number)):  # NUMBER와 TAIL구분
                    if not number[j].isdigit():
                        tail = number[j:]
                        number = number[:j]
                        break
                answer.append([head, number, tail])
                head, number, tail = '', '', ''
                break

    answer.sort(key=lambda x: (x[0].lower(), int(x[1])))

    return [''.join(ans) for ans in answer]