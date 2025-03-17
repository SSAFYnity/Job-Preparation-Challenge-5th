function solution(jobs) {
    let answer = 0;
    let timer = 0;
    let taskQueue = [];
    let jobLength = jobs.length;
    
    jobs = jobs.sort((a, b) => a[0] - b[0])
    
    let idx = 0;
    while(taskQueue.length > 0 || idx < jobLength){
        
        while(idx < jobLength && jobs[idx][0] <= timer){
            taskQueue.push(jobs[idx]);
            idx++;
        }
        //소요시간 적은 순으로 정렬
        taskQueue.sort((a, b) => a[1] - b[1] || a[0] - b[1]);
        
        
        if(taskQueue.length > 0){
            let task = taskQueue.shift();
            
            timer += task[1];
            answer += timer - task[0];
        }
        else {
            timer = jobs[idx][0];
        }
    }
    
   
    
    return Math.floor(answer / jobLength);
}