#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105, MAXX = 1e6 + 5, MOD = 1e9 + 7;

int N, X;
int c[MAXX];
long dp[MAXX];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> X;
    for (int n = 1; n <= N; n++) cin >> c[n];

    fill(dp, dp + X + 1, 0);
    dp[0] = 1;
    for (int x = 1; x <= X; x++)
        for (int n = 1; n <= N; n++)
            if (c[n] <= x) dp[x] = (dp[x] + dp[x - c[n]]) % MOD;

    cout << dp[X] << '\n';
    
    return 0;
}