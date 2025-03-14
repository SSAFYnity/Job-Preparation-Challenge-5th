import java.util.*;


class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int idx = 0; idx < files.length; idx++){
            pq.offer(new Node(idx, files[idx]));
        }
        
        for(int i = 0; i < files.length; i++){
            answer[i] = pq.poll().fullName;
        }
        return answer;
    }
                    
    public String getHead(String target){
        return target.split("[0-9]+")[0].toLowerCase();
    }
    
    public int getNumber(String target){
        return Integer.parseInt(target.split("[^0-9]+")[1]);
    }
    
      
  class Node implements Comparable<Node>{
      int idx;
      String head;
      int number;
      String fullName;
      
      public Node(int idx, String fullName){
          this.idx = idx;
          this.head = getHead(fullName);
          this.number = getNumber(fullName);
          this.fullName = fullName;
      }
      
      @Override
      public int compareTo(Node target){
             
          int headComparison = this.head.compareTo(target.head);
          if(headComparison != 0)
              return headComparison;
          
          int numberComparison = this.number - target.number;
          if(numberComparison != 0)
              return numberComparison;
          
          return this.idx - target.idx;
      }
    }
}
