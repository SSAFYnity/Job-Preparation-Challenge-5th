def solution(n, build_frame):
    answer = []
    board = [[-1] * (n+1) for _ in range(n+1)]
    
    def check(x,y,a):
        # 기둥
        if a == 0:
            if y == 0 or [x,y-1,0] in answer or [x,y,1] in answer or [x-1, y, 1] in answer:
                return True
        # 보
        else:
            if [x,y-1,0] in answer or [x+1,y-1,0] in answer or ([x-1,y,1] in answer and [x+1,y,1] in answer):
                return True
            
        return False
                
    
    for x,y,a,b in build_frame:
        # 삭제
        if b == 0:
            answer.remove([x,y,a])
            for nx,ny,na in answer:
                if not check(nx,ny,na):
                    answer.append([x,y,a])
                    break
        # 설치
        if b == 1:
            if check(x,y,a):
                answer.append([x,y,a])

    answer.sort()
    
    return answer