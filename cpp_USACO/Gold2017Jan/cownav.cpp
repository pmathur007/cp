#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 22;
int di[]{0, -1, 0, 1};
int dj[]{1, 0, -1, 0};

int N;
char board[MAXN][MAXN];
bool visited[MAXN][MAXN][5][MAXN][MAXN][5];

struct state {
    int ri, rj, rd, ui, uj, ud;
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("cownav.in", "r", stdin);
    freopen("cownav.out", "w", stdout);

    cin >> N;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> board[i][j];

    queue<pair<int, state>> q;
    q.push({0, {N - 1, 0, 0, N - 1, 0, 1}});
    visited[N - 1][0][0][N - 1][0][1] = true;
    while (!q.empty()) {
        pair<int, state> v = q.front(); q.pop();

        if (v.s.ri == 0 && v.s.rj == N - 1 && v.s.ui == 0 && v.s.uj == N - 1) {
            cout << v.f << '\n';
            break;
        }

        int nri = v.s.ri, nrj = v.s.rj, nrd = v.s.rd, nui = v.s.ui, nuj = v.s.uj, nud = v.s.ud;
        if (nri != 0 || nrj != N - 1) {
            if (nri + di[nrd] >= 0 && nri + di[nrd] < N) nri += di[nrd];
            if (nrj + dj[nrd] >= 0 && nrj + dj[nrd] < N) nrj += dj[nrd];
            if (board[nri][nrj] == 'H') {
                nri = v.s.ri;
                nrj = v.s.rj;
            }
        }
        if (nui != 0 || nuj != N - 1) {
            if (nui + di[nud] >= 0 && nui + di[nud] < N) nui += di[nud];
            if (nuj + dj[nud] >= 0 && nuj + dj[nud] < N) nuj += dj[nud];
            if (board[nui][nuj] == 'H') {
                nui = v.s.ui; nuj = v.s.uj;
            }
        }
        if (!visited[nri][nrj][nrd][nui][nuj][nud]) {
            visited[nri][nrj][nrd][nui][nuj][nud] = true;
            q.push({v.f + 1, {nri, nrj, nrd, nui, nuj, nud}});
        }

        nri = v.s.ri, nrj = v.s.rj, nrd = (v.s.rd + 1) % 4, nui = v.s.ui, nuj = v.s.uj, nud = (v.s.ud + 1) % 4;
        if (!visited[nri][nrj][nrd][nui][nuj][nud]) {
            visited[nri][nrj][nrd][nui][nuj][nud] = true;
            q.push({v.f + 1, {nri, nrj, nrd, nui, nuj, nud}});
        }

        nri = v.s.ri, nrj = v.s.rj, nrd = (v.s.rd + 3) % 4, nui = v.s.ui, nuj = v.s.uj, nud = (v.s.ud + 3) % 4;
        if (!visited[nri][nrj][nrd][nui][nuj][nud]) {
            visited[nri][nrj][nrd][nui][nuj][nud] = true;
            q.push({v.f + 1, {nri, nrj, nrd, nui, nuj, nud}});
        }
    }
    
    return 0;
}