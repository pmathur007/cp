#include  "bits/stdc++.h"
using namespace std;

const int MAXN = 1005, MOD = 1e9+9;

int N, M, K;
int fj[MAXN], fp[MAXN];
long dp[MAXN][MAXN][11], pre[MAXN][MAXN][11];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("team.in", "r", stdin);
    freopen("team.out", "w", stdout);

    cin >> N >> M >> K;
    for (int i = 1; i <= N; i++) cin >> fj[i];
    for (int i = 1; i <= M; i++) cin >> fp[i];
    sort(fj, fj + N); sort(fp, fp + M);

    for (int n = 1; n <= N; n++) {
        for (int m = 1; m <= M; m++) {
            for (int k = 1; k <= K; k++) {
                dp[n][m][k] = 0;
                if (fj[n] > fp[m]) dp[n][m][k] = k == 1 ? 1 : pre[n - 1][m - 1][k - 1];
                pre[n][m][k] = (((((dp[n][m][k] + pre[n - 1][m][k]) % MOD) + pre[n][m - 1][k]) % MOD) - pre[n - 1][m - 1][k] + MOD) % MOD;
            }
        }
    }

    cout << pre[N][M][K] << '\n';
    
    return 0;
}