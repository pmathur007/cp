#include "bits/stdc++.h"
using namespace std;

int N, K;
long a[205];
long p[105][105], t[105][105];
long dp[105][10][105];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("cbarn2.in", "r", stdin);
    freopen("cbarn2.out", "w", stdout);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) {
        long ai; cin >> ai;
        a[i] = a[i + N] = ai;
    }

    for (int s = 1; s <= N; s++) {
        p[s][0] = t[s][0] = 0;
        for (int i = 0; i < N; i++) {
            p[s][i + 1] = p[s][i] + a[s + i];
            t[s][i + 1] = t[s][i] + i * a[s + i];
        }
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                if (k == 1) dp[s][k][n] = t[s][n];
                else {
                    dp[s][k][n] = LONG_MAX;
                    for (int i = n - 1; i >= 0; i--)
                        dp[s][k][n] = min(dp[s][k][n], dp[s][k - 1][i] + t[s][n] - t[s][i] - i * (p[s][n] - p[s][i]));
                }
            }
        }
    }

    long ans = LONG_MAX;
    for (int s = 1; s <= N; s++) ans = min(ans, dp[s][K][N]);
    cout << ans << '\n';

    return 0;
}