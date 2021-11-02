#include "bits/stdc++.h"
using namespace std;

const int MAXNK = 5005, MAXM = 1e5 + 5, MOD = 1e9 + 7;

int N, M, K;
int s[MAXNK], c[MAXNK];
int freq[27];
long long dp[MAXNK];
long long r[MAXNK];

long long exp(long long b, long long p) {
    if (p == 0) return 1;
    if (p == 1) return (b + MOD) % MOD;
    long long ans = exp(b, p / 2);
    ans = (ans * ans + MOD) % MOD;
    if (p % 2 == 1) ans = (ans * b + MOD) % MOD;
    return (ans + MOD) % MOD;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("poetry.in", "r", stdin);
    freopen("poetry.out", "w", stdout);

    cin >> N >> M >> K;
    for (int n = 1; n <= N; n++) cin >> s[n] >> c[n];
    for (int m = 1; m <= M; m++) {
        char e; cin >> e;
        freq[e - 'A' + 1]++;
    }

    dp[0] = 1;
    for (int k = 1; k <= K; k++)
        for (int n = 1; n <= N; n++)
            if (s[n] <= k) dp[k] = (dp[k] + dp[k - s[n]] + MOD) % MOD;

    for (int n = 1; n <= N; n++) r[c[n]] = (r[c[n]] + dp[K - s[n]]) % MOD;

    long long ans = 1;
    for (int e = 1; e <= 26; e++) {
        long long t = 0;
        if (freq[e] > 0) {
            for (int n = 1; n <= N; n++) t = (t + exp(r[n], freq[e]) + MOD) % MOD;
            ans = (ans * t + MOD) % MOD;
        }
    }
    cout << ans << '\n';

    return 0;
}