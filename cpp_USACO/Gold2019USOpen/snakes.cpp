#include "bits/stdc++.h"
using namespace std;

const int MAXN = 405;

int N, K;
int a[MAXN];
int dp[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("snakes.in", "r", stdin);
    freopen("snakes.out", "w", stdout);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) cin >> a[i];

    int m = 0, s = 0;
    for (int n = 1; n <= N; n++) {
        m = max(m, a[n]);
        s += a[n];
        dp[0][n] = n * m - s;
    }

    for (int k = 1; k <= K; k++) {
        for (int n = 1; n <= N; n++) {
            m = 0; s = 0;
            dp[k][n] = INT_MAX;
            for (int i = n - 1; i >= 0; i--) {
                m = max(m, a[i + 1]);
                s += a[i + 1];
                dp[k][n] = min(dp[k][n], dp[k - 1][i] + m * (n - i) - s);
            }
        }
    }

    cout << dp[K][N] << '\n';

    return 0;
}