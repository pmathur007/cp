#include "bits/stdc++.h"
using namespace std;

const int MAXN = 255;

int N, W;
int w[MAXN], t[MAXN];

bool works(int k) {
    vector<vector<long long>> dp(N + 1, vector<long long>(W + 1, LONG_LONG_MIN));
    for (int n = 0; n <= N; n++) dp[n][0] = 0;
    for (int n = 0; n < N; n++) {
        for (int m = 0; m <= W; m++) {
            if (dp[n][m] != LONG_LONG_MIN) {
                dp[n + 1][m] = max(dp[n + 1][m], dp[n][m]);
                dp[n + 1][min(m + w[n + 1], W)] = max(dp[n + 1][min(m + w[n + 1], W)], dp[n][m] + 1000 * t[n + 1] - k * w[n + 1]);
            }
        }
    }
    return dp[N][W] >= 0;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
//    freopen("talent.in", "r", stdin);
//    freopen("talent.out", "w", stdout);

    cin >> N >> W;
    for (int i = 1; i <= N; i++) cin >> w[i] >> t[i];

    int l = 0, r = 250 * 1000 * 1000 + 1;
    while (l + 1 < r) {
        int mid = (l + r) / 2;
        if (works(mid)) l = mid;
        else r = mid;
    }

    cout << l << '\n';
    
    return 0;
}