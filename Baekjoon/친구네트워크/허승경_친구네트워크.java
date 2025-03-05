import java.io.*;
import java.util.*;

public class Main {
	 static HashMap<String, String> parentMap;  // 부모 저장
	 static HashMap<String, Integer> networkSize; // 각 네트워크 크기 저장

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			parentMap = new HashMap<>();
			networkSize = new HashMap<>();
			
			for(int i = 0; i < n; i++) {
				String [] tmp = br.readLine().split(" ");
				String f1 = tmp[0];
				String f2 = tmp[1];
				
				if(!parentMap.containsKey(f1)) {
					parentMap.put(f1, f1);
					networkSize.put(f1, 1);
				}
				if(!parentMap.containsKey(f2)) {
					parentMap.put(f2, f2);
					networkSize.put(f2, 1);
				}
				
				// 같은 네트워크로 합치기
				sb.append(union(f1, f2)+"\n");
			}	
		}
		System.out.println(sb.toString());	
	}
	
	 // 부모 찾기 
    static String findParent(String child) {
        if (!child.equals(parentMap.get(child))) {
        	parentMap.put(child, findParent(parentMap.get(child)));  // 경로 압축
        }
        return parentMap.get(child);
    }
	
    // 두 노드를 같은 네트워크로 합치고 크기 반환
    static int union(String f1, String f2) {
        String root1 = findParent(f1);
        String root2 = findParent(f2);

        if (!root1.equals(root2)) {
            // 네트워크 크기가 더 큰 쪽에 합치기
            if (networkSize.get(root1) < networkSize.get(root2)) {
                parentMap.put(root1, root2);
                networkSize.put(root2, networkSize.get(root1) + networkSize.get(root2));
                return networkSize.get(findParent(root2));  // 부모 갱신 후 크기 반환
            } else {
                parentMap.put(root2, root1);
                networkSize.put(root1, networkSize.get(root1) + networkSize.get(root2));
                return networkSize.get(findParent(root1));  // 부모 갱신 후 크기 반환
            }
        }
        return networkSize.get(root1);
    }
}
