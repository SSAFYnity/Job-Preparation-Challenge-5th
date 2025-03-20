#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    // userID에 nickname 매핑
    unordered_map<string, string> nicknameMap;
    
    /* Enter/Leave 로그만 저장하는 벡터
     * first: userID
     * second: 행동(Enter/Leave)
    */
    vector<pair<string, string>> logs;

    // 문자열 파싱: 공백을 찾아 분할
    for (const auto &r : record) {
        // 첫 번째 공백 찾기
        int firstSpace = r.find(' ');
        string action = r.substr(0, firstSpace);

        // 두 번째 공백 찾기 (uid 뒤에 올 위치)
        int secondSpace = r.find(' ', firstSpace + 1);
        string uid = r.substr(firstSpace + 1, secondSpace - (firstSpace + 1));

        // action에 따라 name 유무 결정
        if (action == "Enter") {
            // 세 번째 공백 이후부터 끝까지가 name
            string name = r.substr(secondSpace + 1);
            // 닉네임 갱신
            nicknameMap[uid] = name;
            // Enter 로그 저장
            logs.push_back({uid, action});
        } else if (action == "Leave") {
            // Leave 로그 저장
            logs.push_back({uid, action});
        } else if (action == "Change") {
            // 세 번째 공백 이후부터 끝까지가 name
            string name = r.substr(secondSpace + 1);
            // 닉네임 갱신
            nicknameMap[uid] = name;
        }
    }

    // 저장해둔 Enter/Leave 로그를 최종 닉네임으로 치환해 출력 문구 생성
    for (auto &log : logs) {
        string uid = log.first;
        string action = log.second;
        if (action == "Enter") {
            answer.push_back(nicknameMap[uid] + "님이 들어왔습니다.");
        } else if (action == "Leave") {
            answer.push_back(nicknameMap[uid] + "님이 나갔습니다.");
        }
    }
    
    return answer;
}
