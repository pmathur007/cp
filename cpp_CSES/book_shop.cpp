#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005, MAXX = 1e5 + 5;

int N, X;
int h[MAXN], s[MAXN];
int dp[MAXN][MAXX];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> X;
    for (int n = 1; n <= N; n++) cin >> h[n];
    for (int n = 1; n <= N; n++) cin >> s[n];

    for (int n = 1; n <= N; n++) {
        for (int x = 1; x <= X; x++) {
            dp[n][x] = max(dp[n][x], dp[n - 1][x]);
            if (h[n] <= x) dp[n][x] = max(dp[n][x], dp[n - 1][x - h[n]] + s[n]);
        }
    }

    cout << dp[N][X] << '\n';
    return 0;
}