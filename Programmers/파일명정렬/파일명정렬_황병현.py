def solution(files):
    answer = []
    lst = []
    for i in range(len(files)):
        check1 = False
        check2 = False
        check_lst = []
        word = ""
        num = ""
        ect = ""
        for j in range(len(files[i])):
            s = files[i][j]
            if s.isdigit() and len(num) <= 4 and not check2:
                check1 = True
                num += s
            elif not check1:
                word += s
            else:
                check2 = True
                ect += s
        check_lst.append(word.lower())
        check_lst.append(int(num))
        check_lst.append(ect)
        check_lst.append(i)
        lst.append(check_lst)
    
    lst.sort(key=lambda x : (x[0],x[1],x[3]))
    for _,_,_,num in lst:
        answer.append(files[num])
    return answer