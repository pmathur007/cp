#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105, MAXX = 1e6 + 5;

int N, X;
int c[MAXN], dp[MAXX];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> X;
    for (int n = 1; n <= N; n++) cin >> c[n];

    fill(dp, dp + X + 1, INT_MAX);
    dp[0] = 0;
    for (int n = 1; n <= N; n++)
        for (int x = c[n]; x <= X; x++)
            if (dp[x - c[n]] != INT_MAX)
                dp[x] = min(dp[x], dp[x - c[n]] + 1);

    cout << (dp[X] == INT_MAX ? -1 : dp[X]) << '\n';
    
    return 0;
}