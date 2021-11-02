#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005;

int N;
int l[MAXN], r[MAXN];
int dp[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("nocross.in", "r", stdin);
    freopen("nocross.out", "w", stdout);

    cin >> N;
    for (int n = 1; n <= N; n++) cin >> l[n];
    for (int n = 1; n <= N; n++) cin >> r[n];

    for (int n = 1; n <= N; n++) {
        for (int m = 1; m <= N; m++) {
            dp[n][m] = max(dp[n - 1][m], dp[n][m - 1]);
            if (abs(l[n] - r[m]) <= 4) dp[n][m] = max(dp[n][m], dp[n - 1][m - 1] + 1);
        }
    }

    cout << dp[N][N] << '\n';
    
    return 0;
}