package Baekjoon.친구네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 김범규_친구네트워크 {
    static Map<String, String> network;
    static Map<String, Integer> size;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            network = new HashMap<>();
            size = new HashMap<>();
            int F = Integer.parseInt(br.readLine());

            for(int f = 0; f < F; f++){
                st = new StringTokenizer(br.readLine());
                String person1 = st.nextToken();
                String person2 = st.nextToken();

                System.out.println(union(person1, person2));
            }
        }
    }

    public static String find(String person){
        //해당 키를 가지고 있지 않다면
        if(!network.containsKey(person)){
            network.put(person, person);
            size.put(person, 1);
        }

        if(!person.equals(network.get(person))){
            network.put(person, find(network.get(person)));
        }

        return network.get(person);
    }

    public static int union(String person1, String person2){
        String root1 = find(person1);
        String root2 = find(person2);

        if(!root1.equals(root2)){
            network.put(root2, root1);
            size.put(root1, size.get(root1) + size.get(root2));
        }

        return size.get(find(person1));
    }
}
