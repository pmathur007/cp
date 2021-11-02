#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005, MOD = 1e9 + 7;

int N;
char grid[MAXN][MAXN];
long dp[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            cin >> grid[i][j];

    dp[1][1] = grid[1][1] == '.';
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            if (i != 1 || j != 1)
                dp[i][j] = grid[i][j] == '*' ? 0 : (dp[i - 1][j] + dp[i][j - 1] + MOD) % MOD;

    cout << dp[N][N] << '\n';
    
    return 0;
}