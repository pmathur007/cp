#include "bits/stdc++.h"
using namespace std;

const int MAXN = 100005;

int N, K;
int a[MAXN];
int dp[MAXN][21][3];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("hps.in", "r", stdin);
    freopen("hps.out", "w", stdout);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) {
        char ai; cin >> ai;
        a[i] = (ai == 'H' ? 0 : (ai == 'P' ? 1 : 2));
    }

    for (int n = 0; n <= N; n++)
        for (int k = 0; k <= K; k++)
            for (int i = 0; i < 3; i++)
                dp[n][k][i] = k == 0 && n >= 1 ? dp[n - 1][k][i] + (a[n] == ((i + 2) % 3)) : 0;

    for (int n = 1; n <= N; n++) {
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < 3; i++) {
                dp[n][k][i] = max(dp[n - 1][k][i], max(dp[n - 1][k - 1][(i + 1) % 3], dp[n - 1][k - 1][(i + 2) % 3])) + (a[n] == ((i + 2) % 3));
            }
        }
    }

    cout << max(dp[N][K][0], max(dp[N][K][1], dp[N][K][2])) << '\n';

    return 0;
}