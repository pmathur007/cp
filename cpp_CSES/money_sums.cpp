#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105, MAXK = 1e5 + 5;

int N;
int x[MAXN];
bool dp[MAXN][MAXK];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int n = 1; n <= N; n++) cin >> x[n];

    for (int n = 0; n <= N; n++) dp[n][0] = true;
    for (int n = 1; n <= N; n++) {
        for (int k = 1; k < MAXK; k++) {
            dp[n][k] |= dp[n - 1][k];
            if (x[n] <= k) dp[n][k] |= dp[n - 1][k - x[n]];
        }
    }

    vector<int> ans;
    for (int k = 1; k <= MAXK; k++)
        if (dp[N][k]) ans.push_back(k);

    cout << ans.size() << '\n';
    for (int i = 0; i < ans.size(); i++) cout << ans[i] << (i == ans.size() - 1 ? '\n' : ' ');

    return 0;
}