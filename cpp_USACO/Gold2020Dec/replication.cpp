#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1005;
const int di[]{0, -1, 0, 1};
const int dj[]{1, 0, -1, 0};

int N, D;
char board[MAXN][MAXN];
int maxs[MAXN][MAXN], viss[MAXN][MAXN];
bool marked[MAXN][MAXN];

bool valid(int i, int j) {
    return (i >= 0 && i < N && j >= 0 && j < N && board[i][j] != '#');
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> D;

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> board[i][j];

    queue<pair<int, int>> sq;
    queue<pair<int, pair<int, int>>> vq;
    priority_queue<pair<int, pair<int, int>>> starts;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (board[i][j] == '#') {
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d], nj = j + dj[d];
                    if (valid(ni, nj) && maxs[ni][nj] == 0) {
                        sq.push({ni, nj});
                        maxs[ni][nj] = 1;
                    }
                }
            }
        }
    }

    while (!sq.empty()) {
        pair<int, int> p = sq.front(); sq.pop();
        if (board[p.f][p.s] == 'S') {
            vq.push({D, {p.f, p.s}});
            viss[p.f][p.s] = maxs[p.f][p.s];
            starts.push({maxs[p.f][p.s], {p.f, p.s}});
        }
        for (int d = 0; d < 4; d++) {
            int ni = p.f + di[d], nj = p.s + dj[d];
            if (valid(ni, nj) && maxs[ni][nj] == 0) {
                maxs[ni][nj] = maxs[p.f][p.s] + 1;
                sq.push({ni, nj});
            }
        }
    }

    while (!vq.empty()) {
        pair<int, pair<int, int>> p = vq.front(); vq.pop();
        for (int d = 0; d < 4; d++) {
            int ni = p.s.f + di[d], nj = p.s.s + dj[d];
            if (valid(ni, nj) && viss[ni][nj] == 0 && p.f / D <= maxs[ni][nj]) {
                viss[ni][nj] = maxs[ni][nj];
                if ((p.f + 1) / D <= maxs[ni][nj])
                    vq.push({p.f + 1, {ni, nj}});
                starts.push({maxs[ni][nj], {ni, nj}});
//                mq.push({ni, nj});
//                marked[ni][nj] = true;
            }
        }
    }

    queue<pair<int, pair<int, int>>> mq;
    for (int curd = starts.top().f; curd > 0; curd--) {
        while (!starts.empty() && starts.top().f == curd) {
            pair<int, pair<int, int>> p = starts.top(); starts.pop();
//            cout << p.f << ": (" << p.s.f << ", " << p.s.s << ")\n";
            if (!marked[p.s.f][p.s.s]) {
                mq.push(p);
                marked[p.s.f][p.s.s] = true;
            }
        }
        while (!mq.empty() && mq.front().f == curd) {
            pair<int, pair<int, int>> p = mq.front(); mq.pop();
            for (int d = 0; d < 4; d++) {
                int ni = p.s.f + di[d], nj = p.s.s + dj[d];
                if (valid(ni, nj) && !marked[ni][nj] && p.f > 1) {
                    mq.push({p.f - 1, {ni, nj}});
                    marked[ni][nj] = true;
                }
            }
        }
    }

//    for (int i = 0; i < N; i++) {
//        for (int j = 0; j < N; j++) cout << viss[i][j];
//        cout << '\n';
//    }

    int ans = 0;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            ans += marked[i][j];

    cout << ans << '\n';

    return 0;
}