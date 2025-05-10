#include <iostream>
#include <deque>
#include <unordered_map>
#include <unordered_set>

using namespace std;

struct Node {
    int y, x;
    bool operator==(const Node& right) const {
        return (y == right.y && x == right.x);
    }
};

struct MyHash {
    size_t operator()(const Node& node) const {
		hash<int> hash_func;
		return hash_func(node.y) ^ node.x;
    }
};

int dy[4] = { 0,1,0,-1 };
int dx[4] = { 1,0,-1,0 };

int main() {
    int N;
    cin >> N;
    
    unordered_set<Node, MyHash> apples;

    int K;
    cin >> K;
    for (int i = 0; i < K; i++) {
        int r, c;
        cin >> r >> c;
        
        apples.insert({r - 1, c - 1});
    }

    int L;
    cin >> L;
    
    unordered_map<int, char> changes;
    for (int i = 0; i < L; i++) {
        int t;
        char dirC;
        cin >> t >> dirC; 
        changes[t] = dirC;
    }
    
    int dir = 0;
    int time = 0;
    
    deque<Node> snake;
    snake.push_back({0, 0});
    
    bool gameOver = false;
    while (!gameOver) {
        time++;
        
        Node head = snake.back();
        
        head.y += dy[dir];
        head.x += dx[dir];
        
        if (head.y < 0 ||
            head.y >= N ||
            head.x < 0 ||
            head.x >= N){
            gameOver = true;
            break;
        }
        
        for (const auto& bodyPos : snake) {
            if (bodyPos == head) {
                gameOver = true;
                break;
            }
        }
        if (gameOver) break;
        
        if (apples.find(head) != apples.end()) {
            apples.erase(head);
        } else {
            snake.pop_front();
        }
        
        snake.push_back(head);
        
        if (changes.find(time) != changes.end()) {
            char turnC = changes[time];
            
            if (turnC == 'L') {
                dir = (dir + 3) % 4;
            } else { // 'D'
                dir = (dir + 1) % 4;
            }
        }
    }

    cout << time << "\n";
    
    return 0;
}
