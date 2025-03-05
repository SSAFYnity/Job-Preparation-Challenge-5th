import java.util.*;
import java.io.*;
 
public class Main {
    public static class Node {
        Node parent;
        String name;
        int count; // 현재 노드 포함 자손 노드 수
        
        public Node(String name) {
            this.name = name;
            this.count = 1;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            int F = Integer.parseInt(br.readLine());
            
            Map<String, Node> nodes = new HashMap<>();
            
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String[] names = new String[] {st.nextToken(), st.nextToken()};
                
                // 노드가 없는 경우 새 노드 생성
                for (String name: names) {
                    if (nodes.containsKey(name)) {
                        continue;
                    }
                    
                    Node node = new Node(name);
                    nodes.put(name, node);
                }
                
                // Node parent = find(nodes.get(names[0]));
                // Node child = find(nodes.get(names[1]));
                
                // union(parent, child);
                union(nodes.get(names[0]), nodes.get(names[1]));
                Node parent = find(nodes.get(names[0]));
                sb.append(parent.count).append("\n");
            }
        }
        
        System.out.println(sb);
    }
    
    // 그룹 대표 노드 찾기
    public static Node find(Node node) {
        if (node.parent == null) {
            return node;
        }
        
        return node.parent = find(node.parent);
    }
    
    // 그룹 합치기
    public static void union(Node a, Node b) {
        Node aParent = find(a);
        Node bParent = find(b);
        
        if (aParent.name.equals(bParent.name)) {
            return;
        }
        
        // System.out.println("a: "+ a.name + " | aPrent: "+ aParent.name + " ||| b: "+ b.name + " | bPaarent: "+ bParent.name);
        // a 그룹에 b 그룹 넣기
        bParent.parent = aParent;
        aParent.count += bParent.count;
    }
    
}
