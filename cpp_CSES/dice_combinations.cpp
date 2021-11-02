#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e6 + 5, MOD = 1e9 + 7;

int N;
long dp[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    fill(dp, dp + N + 1, 0);
    dp[0] = 1;
    for (int n = 1; n <= N; n++)
        for (int k = 1; k <= min(6, n); k++)
            dp[n] = (dp[n] + dp[n - k]) % MOD;
    cout << dp[N] << '\n';

    return 0;
}