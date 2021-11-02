#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--) {
        int M; cin >> M;
        vector<int> board(100, -1);
        for (int i = 0; i < M; i++) {
            int o, d; string s; cin >> o >> s >> d;
            board[o] = d;
        }

        int K; cin >> K;
        int cur = 0;
        bool won = false;
        for (int i = 1; i <= K; i++) {
            int k; cin >> k;
            cur = min(cur + k, 99);
            while (board[cur] != -1) cur = board[cur];
            if (cur == 99 && !won) {
                cout << i << "\n";
                won = true;
            }
        }
        if (!won) cout << "Still Playing!" << "\n";
    }

    return 0;
}