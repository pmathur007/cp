#include "bits/stdc++.h"
using namespace std;

const int MAXT = 5e6 + 5;

int T, A, B;
bool dp[MAXT];
int pre[MAXT];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("feast.in", "r", stdin);
    freopen("feast.out", "w", stdout);

    cin >> T >> A >> B;

    dp[0] = true;
    for (int t = 1; t <= T; t++) dp[t] = t % A == 0;
    for (int t = B; t <= T; t++) dp[t] = dp[t] || dp[t - B];

    pre[0] = 0;
    for (int t = 1; t <= T; t++) pre[t] = dp[t] ? t : pre[t - 1];

    int ans = 0;
    for (int t = 1; t <= T; t++) {
        if (dp[t]) {
            ans = max(ans, t);
            ans = max(ans, t / 2 + pre[T - (t /2)]);
        }
    }

    cout << ans << '\n';
    
    return 0;
}