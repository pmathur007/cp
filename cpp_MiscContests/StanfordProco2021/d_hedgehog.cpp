#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXNM = 405;

int N, M;
bool b[MAXNM][MAXNM];
bool visited[MAXNM][MAXNM][25][5];

struct state {
    int i{}, j{}, v{}, d{};
};

int di[]{0, -1, 0, 1};
int dj[]{1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            char c; cin >> c;
            b[i][j] = c == '1';
        }
    }

    queue<pair<int, state>> q;    
    q.push({0, {0, 0, 0, 3}});
    visited[0][0][0][3] = true;

    while (!q.empty()) {
        pair<int, state> p = q.front(); q.pop();
        // cout << p.f << ": " << p.s.i << " " << p.s.j << " " << p.s.v << " " << p.s.d << "\n";

        if (p.s.i == N - 1 && p.s.j == M - 1) {
            cout << p.f << "\n";
            return 0;
        }

        // if (visited[p.s.i][p.s.j][p.s.v][p.s.d]) continue;
        // visited[p.s.i][p.s.j][p.s.v][p.s.d] = true;

        if (p.s.v > 0) {
            int ni = p.s.i + di[p.s.d] * (p.s.v + 1), nj = p.s.j + dj[p.s.d] * (p.s.v + 1);
            if (ni >= 0 && ni < N && nj >= 0 && nj < M && !visited[ni][nj][p.s.v + 1][p.s.d]) {
                bool civ = false;
                for (int k = 1; k <= p.s.v + 1; k++) {
                    if (b[p.s.i + k * di[p.s.d]][p.s.j + k * dj[p.s.d]]) {
                        civ = true;
                        break;
                    }
                }
                if (!civ) {
                    q.push({p.f + 1, {ni, nj, p.s.v + 1, p.s.d}});
                    visited[ni][nj][p.s.v + 1][p.s.d] = true;
                }
            }

            ni = p.s.i + di[p.s.d] * (p.s.v - 1), nj = p.s.j + dj[p.s.d] * (p.s.v - 1);
            if (ni >= 0 && ni < N && nj >= 0 && nj < M && !visited[ni][nj][p.s.v - 1][p.s.d]) {
                bool civ = false;
                for (int k = 1; k <= p.s.v - 1; k++) {
                    if (b[p.s.i + k * di[p.s.d]][p.s.j + k * dj[p.s.d]]) {
                        civ = true;
                        break;
                    }
                }
                if (!civ) {
                    q.push({p.f + 1, {ni, nj, p.s.v - 1, p.s.d}});
                    visited[ni][nj][p.s.v - 1][p.s.d] = true;
                }
            }
        }
        else {
            for (int m = 1; m <= 3; m++) {
                if (!visited[p.s.i][p.s.j][p.s.v][(p.s.d + m) % 4]) {
                    q.push({p.f + 1, {p.s.i, p.s.j, p.s.v, (p.s.d + m) % 4}});
                    visited[p.s.i][p.s.j][p.s.v][(p.s.d + m) % 4] = true;
                }
            }
            int ni = p.s.i + di[p.s.d], nj = p.s.j + dj[p.s.d];
            if (ni >= 0 && ni < N && nj >= 0 && nj < M && !b[ni][nj] && !visited[ni][nj][p.s.v + 1][p.s.d]) {
                q.push({p.f + 1, {ni, nj, p.s.v + 1, p.s.d}});
                visited[ni][nj][p.s.v + 1][p.s.d] = true;
            }
        }
    }
    
    cout << -1 << "\n";

    return 0;
}