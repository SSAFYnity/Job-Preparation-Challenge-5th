#include <iostream>
#include <string>
#include <string.h>
#include <queue>
#include <algorithm>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int, char> pii;
typedef unsigned long long ll;


int N, K, L;
pii tail;
int arr[101][101];
int way[4][2] = { {-1,0},{0,1},{1,0},{0,-1} };
queue<pii> turn;


int f() {
	int y = 0, x = 0, w = 1;//오른쪽
	arr[y][x] = w;
	tail = { y,x };
	int length = 1, time = 1;
	while (1) {
		y += way[w][0];
		x += way[w][1];
		//머리이동

		if (y < 0 || x < 0 || y >= N || x >= N || (arr[y][x] >= 0 && arr[y][x] < 4)) return time;
		
		if (arr[y][x] == 10) length++;
		else {
			int tw = arr[tail.first][tail.second];
			arr[tail.first][tail.second] = -1;
			tail.first += way[tw][0];
			tail.second += way[tw][1];
		}

		if (!turn.empty() && turn.front().first == time) {
			if (turn.front().second == 'L') w = (w + 4 - 1) % 4;
			else w = (w + 1) % 4;
			turn.pop();
		}
		arr[y][x] = w;

		
		time++;
	
	}
}

int main() {
	//freopen("sample_input.txt", "r", stdin);
	//freopen("out.txt", "w", stdout);

	memset(arr, -1, sizeof(arr));

	scanf("%d %d", &N, &K);
	for (int i = 0; i < K; i++) {
		int y, x;
		scanf("%d %d", &y, &x);
		arr[y-1][x-1] = 10;//사과
	}
	scanf("%d", &L);//쿼리
	for (int i = 0; i < L; i++) {
		int x;
		char c;//x초 끝난뒤 c로 회전
		scanf("%d %c", &x, &c);
		turn.push({ x,c });
	}
	
	int res = f();
	printf("%d\n", res);

	//fclose(stdin);
	//fclose(stdout);
}
