import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, ArrayList<String>> hmap = new HashMap<>();
			
			for(int i = 0; i < n; i++) {
				String [] tmp = br.readLine().split(" ");
				String f1 = tmp[0];
				String f2 = tmp[1];
				
				if(!hmap.containsKey(f1)) {
					ArrayList<String> list = new ArrayList<>();
					list.add(f2);
					hmap.put(f1, list);
				}else {
					hmap.get(f1).add(f2);
				}
				
				if(!hmap.containsKey(f2)) {
					ArrayList<String> list = new ArrayList<>();
					list.add(f1);
					hmap.put(f2, list);
				}else {
					hmap.get(f2).add(f1);
				}
				
				// 네트워크 구하기
				Queue<String> que = new LinkedList<>();
				HashSet<String> set = new HashSet<>();	// 확인한 친구
				que.add(f1);
				set.add(f1);
				
				while(!que.isEmpty()) {
					String curName = que.poll();
					
					if(hmap.get(curName).size() > 0) {
						ArrayList<String> tmpList = hmap.get(curName);
						for(int idx = 0; idx < tmpList.size(); idx++) {
							if(!set.contains(tmpList.get(idx))) {
								que.add(tmpList.get(idx));
								set.add(tmpList.get(idx));
							}
						}
					}
				}
				
				
				sb.append(set.size()+"\n");
			}
			System.out.println(sb.toString());		
		}
	}
}
