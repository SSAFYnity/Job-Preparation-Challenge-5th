/*
 * 아이디어 : 구현
 * 1. a에 먼저 부을 때
 * - a에 옮긴양을 b에 전부 옮길 수 있음 -> 성공 -> 현재 c 용량 추가
 * - 현재 c용량을 a에 옮길 수 있음 && c 용량을 b에 옮길 수 있음 && b 용량을 c에 옮길 수 있음 -> 현재 b 용량 추가
 * 2. b에 먼저 부을 때
 * - 현재 c에 남은 용량 추가
 * - 현재 c의 용량 a에 전부 옮길 수 있음 && b에 용량 전부 c에 옮길 수 있음 && 현재 c의 용량 b에 옮길 수 있음 -> 현재 b의 용량 추가
 * */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] arr = new int[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> set = new HashSet<>();
		set.add(arr[2]);
		
		// 구현
		for(int i = 0; i < 2; i++) {
			int [] tmp = new int[3];
			if(i == 0) {
				if(arr[0] >= arr[2]) {
					tmp[0] = arr[2];	// 전부 붓기
				}else {
					tmp[0] = arr[0];	// a 최대 용량 붓기
				}
				tmp[2] = arr[2]-tmp[0];
				
				if(arr[1] >= tmp[0]) {
					tmp[1] = tmp[0];	// 전부 붓기
					tmp[0] = 0;
				}else {
					tmp[1] = arr[1];	// a 최대 용량 붓기
					tmp[2] += (tmp[0]-tmp[1]);
					tmp[0] = 0;
				}
				set.add(tmp[2]);
				
				if(arr[1] >= tmp[2] && arr[2] >= tmp[1] && arr[0] >=tmp[2]) {
					set.add(tmp[1]);
				}
			}else {
				if(arr[1] >= arr[2]) {
					tmp[1] = arr[2];	// 전부 붓기
				}else {
					tmp[1] = arr[1];	// a 최대 용량 붓기
				}
				tmp[2] = arr[2]-tmp[1];
				set.add(tmp[2]);
				if(arr[0] >= tmp[2] && arr[2] >= tmp[1] && arr[1] >=tmp[2]) {
					set.add(tmp[1]);
				}
			}
		}
		
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int num : list) {
			sb.append(num+" ");
		}
		
		System.out.println(sb.toString());
	}

}
