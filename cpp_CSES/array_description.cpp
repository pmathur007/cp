#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MAXM = 105, MOD = 1e9 + 7;

int N, M;
int x[MAXN];
long dp[MAXN][MAXM];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int n = 1; n <= N; n++) cin >> x[n];

    for (int m = 1; m <= M; m++) dp[1][m] = x[1] == 0 || x[1] == m;
    for (int n = 2; n <= N; n++) {
        for (int m = 1; m <= M; m++) {
            if (x[n] != 0 && x[n] != m) dp[n][m] = 0;
            else {
                dp[n][m] = dp[n - 1][m];
                if (m > 1) dp[n][m] = (dp[n][m] + dp[n - 1][m - 1] + MOD) % MOD;
                if (m < M) dp[n][m] = (dp[n][m] + dp[n - 1][m + 1] + MOD) % MOD;
            }
        }
    }

    long ans = 0;
    for (int m = 1; m <= M; m++) ans = (ans + dp[N][m] + MOD) % MOD;
    cout << ans << '\n';

    return 0;
}