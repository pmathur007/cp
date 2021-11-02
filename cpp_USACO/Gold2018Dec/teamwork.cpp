#include "bits/stdc++.h"
using namespace std;

const int MAXN = 10005, MAXK = 1005;

int N, K;
long s[MAXN], dp[MAXN];
long sint[MAXN][MAXK];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("teamwork.in", "r", stdin);
    freopen("teamwork.out", "w", stdout);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) cin >> s[i];

    for (int i = 1; i <= N; i++) sint[i][1] = s[i];
    for (int k = 2; k <= K; k++)
        for (int i = 1; i <= N - k + 1; i++)
            sint[i][k] = max(sint[i][k - 1], sint[i + 1][k - 1]);

    fill(dp, dp + MAXN, 0);
    for (int i = 1; i <= N; i++)
        for (int j = 0; i - j >= 0 && j <= K; j++)
            dp[i] = max(dp[i], dp[i - j] + j * sint[i - j + 1][j]);

    cout << dp[N] << '\n';
    return 0;
}