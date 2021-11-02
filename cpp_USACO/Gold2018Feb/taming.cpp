#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105;

int N;
int a[MAXN];
int diff[MAXN][MAXN], dp[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("taming.in", "r", stdin);
    freopen("taming.out", "w", stdout);

    cin >> N;
    for (int i = 1; i <= N; i++) cin >> a[i];

    for (int i = 1; i <= N; i++)
        for (int j = i; j <= N; j++)
            for (int k = i; k <= j; k++)
                diff[i][j] += (a[k] != k - i);

    for (int n = 1; n <= N; n++) dp[1][n] = diff[1][n];
    for (int k = 2; k <= N; k++) {
        for (int n = k; n <= N; n++) {
            dp[k][n] = INT_MAX;
            for (int i = k; i <= n; i++) {
                dp[k][n] = min(dp[k][n], dp[k - 1][i - 1] + diff[i][n]);
            }
        }
    }

    for (int k = 1; k <= N; k++) cout << dp[k][N] << '\n';
    
    return 0;
}