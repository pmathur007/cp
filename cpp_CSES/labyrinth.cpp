#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXNM = 1005;
int dx[]{0, -1, 0, 1};
int dy[]{1, 0, -1, 0};
char dir[]{'R', 'U', 'L', 'D'};

int N, M;
int ai, aj, bi, bj;
char board[MAXNM][MAXNM];
int p[MAXNM][MAXNM];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> board[i][j];
            if (board[i][j] == 'A') ai = i, aj = j;
            if (board[i][j] == 'B') bi = i, bj = j;
        }
        fill(p[i], p[i] + M, 4);
    }

    set<pair<int, pair<int, int>>> q;
    q.insert({0, {ai, aj}});
    p[ai][aj] = -1;
    bool found = false;
    while (!q.empty()) {
        pair<int, pair<int, int>> v = *q.begin();
        q.erase(q.begin());

        if (v.s.f == bi && v.s.s == bj) {
            cout << "YES" << '\n';
            found = true;
            cout << v.f << '\n';

            string path;
            int i = v.s.f, j = v.s.s;
            while (p[i][j] != -1) {
                path += dir[p[i][j]];
                int ni = i - dx[p[i][j]], nj = j - dy[p[i][j]];
                i = ni, j = nj;
            }

            for (int i = path.length() - 1; i >= 0; i--) cout << path[i];
            cout << '\n';
            break;
        }

        for (int d = 0; d < 4; d++) {
            int nx = v.s.f + dx[d], ny = v.s.s + dy[d];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != '#' && p[nx][ny] == 4) {
                p[nx][ny] = d;
                q.insert({v.f + 1, {nx, ny}});
            }
        }
    }

    if (!found) cout << "NO" << '\n';

    return 0;
}