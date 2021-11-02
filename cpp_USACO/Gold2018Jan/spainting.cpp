#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e6 + 5, MOD = 1e9 + 7;

int N, M, K;
long long dp[MAXN], s[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("spainting.in", "r", stdin);
    freopen("spainting.out", "w", stdout);

    cin >> N >> M >> K;
    dp[0] = 1;
    long long tot = 1;
    for (int n = 1; n <= N; n++) {
        if (n < K) dp[n] = (M * dp[n - 1]) % MOD;
        else dp[n] = ((M - 1) * ((s[n - 1] - s[n - K] + MOD) % MOD)) % MOD;
        s[n] = (s[n - 1] + dp[n]) % MOD;

        tot = (tot * M) % MOD;
    }

    cout << (tot - dp[N] + MOD) % MOD << '\n';
    
    return 0;
}