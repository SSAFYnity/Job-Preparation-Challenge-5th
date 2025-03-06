import java.util.*;

class Node{
    int num, t1, t2; 
    Node(int num, int t1, int t2){
        this.num = num; // 작업 번호
        this.t1 = t1; // 시점
        this.t2 = t2; // 소요시간
        // 소요시간이 덜 걸릴 수록, 시점이 빠를 수록, 작업 번호가 작을 수록 우선순위가 높음
    }
    
    @Override
    public String toString(){
        return "num: " + num + " t1: "+t1+" t2: "+t2;
    }
}

class Job{
    int t1, t2;
    Job(int t1, int t2){
        this.t1 = t1; // 요청시각
        this.t2 = t2; // 소요시간
    }
}

class Solution {
    static PriorityQueue<Node> pq; 
    public int solution(int[][] jobs) {
        int answer = 0;
		int end = 0; 
		int idx = 0;
		int count = 0; // 요청 수행

		// 원본 배열 요청시간 기준오름차순 정렬
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        // 처리시간 순으로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		while (count < jobs.length) {
			// 하나의 작업이 완료되는 시점(end)까지 들어온 모든 요청을 큐에 넣음
			while (idx < jobs.length && jobs[idx][0] <= end) {
				pq.add(jobs[idx++]);
			}

			// 큐가 비어있다면 end를 요청의 가장 처음으로 맞춰줌
			if (pq.isEmpty()) {
				end = jobs[idx][0];
			} else {
				int[] temp = pq.poll();
				answer += temp[1] + end - temp[0];
				end += temp[1];
				count++;
			}
		}

		return (int) Math.floor(answer / jobs.length);
//         // -> 기존 코드는 4번 조건을 충족하지 못한다.
//         pq = new PriorityQueue<Node>((o1, o2)->{
//             if(o1.t2 == o2.t2){
//                 if(o1.t1 == o2.t1){
//                     return o1.num - o2.num;
//                 }
//                 return o1.t1 - o2.t1;
//             }
//             return o1.t2 - o2.t2; // minHeap
//         });
        
//         for(int i=0; i<jobs.length; i++){
//             // 다 담고 시작해도 되나? -> 될 것 같음.
//             pq.add(new Node(i, jobs[i][0], jobs[i][1]));
//         }

//         int willEnd = 0;
//         int sum = 0;
//         while(!pq.isEmpty()){
//             Node next = pq.poll();
//             System.out.println(next.toString());
//             if(next.t1 <= willEnd){
//                 // 요청시각이 반환 예정 시간보다 빠르면 willEnd에 t2 더하기
//                 System.out.println("wd "+willEnd+" t1 "+next.t1);
//                 willEnd += next.t2;
//                 sum += willEnd - next.t1;
//                 System.out.println("end "+willEnd+" , "+(sum));
//             } else {
//                 willEnd = next.t2; // 요청 시각
//                 sum += willEnd - next.t1;
//                 System.out.println("else "+ (sum - next.t1));
//             }
//         }
        
//         int answer = sum/(jobs.length); // 모든 요청 작업의 반환 시간의 평균
//         // 대기큐에 남아있으면(우선순위 높은걸 최우선으로 poll) -> pq 넣기
//         // 작업 마치고 나서 다음 큐 원소 실행 가능
        
//         return answer;
    }
}