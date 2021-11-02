#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second

const int MAXNM = 1005;
int dx[4]{1, 0, -1, 0};
int dy[4]{0, 1, 0, -1};

int N, M;
int fjdir[MAXNM], bdir[MAXNM];
pair<int, int> fjpos[MAXNM], bpos[MAXNM];
long long dp[MAXNM][MAXNM];

long long dist(pair<int, int> p1, pair<int, int> p2) {
    return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("radio.in", "r", stdin);
    freopen("radio.out", "w", stdout);

    cin >> N >> M;
    cin >> fjpos[0].x >> fjpos[0].y >> bpos[0].x >> bpos[0].y;

    for (int n = 1; n <= N; n++) {
        char c; cin >> c;
        if (c == 'E') fjdir[n] = 0;
        else if (c == 'N') fjdir[n] = 1;
        else if (c =='W') fjdir[n] = 2;
        else fjdir[n] = 3;
        fjpos[n] = {fjpos[n - 1].x + dx[fjdir[n]], fjpos[n - 1].y + dy[fjdir[n]]};
        dp[n][0] = dist(fjpos[n], bpos[0]) + dp[n - 1][0];
    }

    for (int m = 1; m <= M; m++) {
        char c; cin >> c;
        if (c == 'E') bdir[m] = 0;
        else if (c == 'N') bdir[m] = 1;
        else if (c =='W') bdir[m] = 2;
        else bdir[m] = 3;
        bpos[m] = {bpos[m - 1].x + dx[bdir[m]], bpos[m - 1].y + dy[bdir[m]]};
        dp[0][m] = dist(bpos[m], fjpos[0]) + dp[0][m - 1];
    }

    for (int n = 1; n <= N; n++)
        for (int m = 1; m <= M; m++)
            dp[n][m] = dist(fjpos[n], bpos[m]) + min(dp[n - 1][m - 1], min(dp[n - 1][m], dp[n][m - 1]));

    cout << dp[N][M] << '\n';

    return 0;
}