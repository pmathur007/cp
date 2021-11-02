#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MAXM = 30;

int N, M, K;
int s[MAXN];
int cst[MAXM][MAXM], pre[MAXN][MAXM];
int dp[MAXN][MAXM], dpmin[MAXN];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
//    freopen("cowmbat.in", "r", stdin);
//    freopen("cowmbat.out", "w", stdout);

    cin >> N >> M >> K;
    for (int n = 1; n <= N; n++) {
        char c; cin >> c;
        s[n] = c - 'a' + 1;
    }

    for (int i = 1; i <= M; i++)
        for (int j = 1; j <= M; j++)
            cin >> cst[i][j];

    for (int k = 1; k <= M; k++)
        for (int i = 1; i <= M; i++)
            for (int j = 1; j <= M; j++)
                cst[i][j] = min(cst[i][j], cst[i][k] + cst[k][j]);

    for (int n = 0; n <= N; n++)
        for (int m = 1; m <= M; m++)
            pre[n][m] = (n == 0 ? 0 : pre[n - 1][m] + cst[s[n]][m]);

    for (int n = 0; n <= N; n++) {
        dpmin[n] = INT_MAX;
        for (int m = 1; m <= M; m++) {
            dp[n][m] = n == 0 ? 0 : dp[n - 1][m] + cst[s[n]][m];
            if (n >= K) dp[n][m] = min(dp[n][m], dpmin[n - K] + pre[n][m] - pre[n - K][m]);
            dpmin[n] = min(dpmin[n], dp[n][m]);
        }
    }

    cout << dpmin[N] << '\n';

    return 0;
}