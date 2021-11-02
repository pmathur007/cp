#include "bits/stdc++.h"
using namespace std;

const int MAXN = 505, MAXK = 1e5 + 5, MOD = 1e9 + 7;

int N, K;
long dp[MAXN][MAXK];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    K = (N * (N + 1)) / 2;
    if (K % 2 == 1) cout << 0 << '\n';
    else {
        K = K / 2;
        for (int n = 0; n <= N; n++) dp[n][0] = 1;
        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                dp[n][k] = (dp[n][k] + dp[n - 1][k]) % MOD;
                if (n <= k) dp[n][k] = (dp[n][k] + dp[n - 1][k - n]) % MOD;
            }
        }
        if (dp[N][K] % 2 == 0) cout << dp[N][K] / 2 << '\n';
        else cout << (dp[N][K] + MOD) / 2 << '\n';
    }

    return 0;
}