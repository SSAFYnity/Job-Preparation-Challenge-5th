#include <string>
#include <vector>

using namespace std;

int K, L, B;

void rotate(vector<vector<int>>& key)
{
    vector<vector<int>> temp(K, vector<int>(K, 0));

    for (int i = 0; i < K; i++)
    {
        for (int j = 0; j < K; j++)
        {
            temp[i][j] = key[K - (j + 1)][i];
        }
    }

    key = temp;
}

bool check(vector<vector<int>>& board, vector<vector<int>>& key, int y, int x)
{
    bool ret = true;

    for (int i = 0; i < K; i++)
    {
        for (int j = 0; j < K; j++)
        {
            board[i + y][j + x] += key[i][j];
        }
    }

    for (int i = 0; i < L; i++)
    {
        for (int j = 0; j < L; j++)
        {
            if (board[i + K - 1][j + K - 1] != 1)
            {
                ret = false;
                break;
            }
        }
    }

    for (int i = 0; i < K; i++)
    {
        for (int j = 0; j < K; j++)
        {
            board[i + y][j + x] -= key[i][j];
        }
    }

    return ret;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    K = key.size(), L = lock.size();
    B = 2 * (K - 1) + L;

    vector<vector<int>> board(B, vector<int>(B, 0));

    for (int i = 0; i < L; i++)
    {
        for (int j = 0; j < L; j++)
        {
            board[i + K - 1][j + K - 1] = lock[i][j];
        }
    }

    for (int r = 0; r < 4; r++)
    {
        for (int i = 0; i <= B - K; i++)
        {
            for (int j = 0; j <= B - K; j++)
            {
                if (check(board, key, i, j))
                    return true;
            }
        }

        rotate(key);
    }

    return false;
}