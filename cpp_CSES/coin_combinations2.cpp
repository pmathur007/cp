#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105, MAXX = 1e6 + 5, MOD = 1e9 + 7;

int N, X;
int c[MAXN];
long dp[MAXX];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> X;
    for (int n = 1; n <= N; n++) cin >> c[n];

    fill(dp, dp + X + 1, 0);
    dp[0] = 1;
    for (int n = 1; n <= N; n++)
        for (int x = c[n]; x <= X; x++)
            dp[x] = (dp[x] + dp[x - c[n]]) % MOD;

    cout << dp[X] << '\n';

    return 0;
}