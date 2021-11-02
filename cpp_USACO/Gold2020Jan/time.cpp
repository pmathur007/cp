#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("time.in", "r", stdin);
    freopen("time.out", "w", stdout);

    int N, M, C; cin >> N >> M >> C;
    vector<int> m(N);
    for (int i = 0; i < N; i++) cin >> m[i];

    vector<vector<int>> graph(N);
    for (int i = 0; i < M; i++)
    {
        int a, b; cin >> a >> b;
        graph[b - 1].push_back(a - 1);
    }

    vector<vector<int>> dp(1001, vector<int>(N, -1));
    dp[0][0] = 0;
    for (int t = 1; t <= 1000; t++)
        for (int i = 0; i < N; i++)
            for (auto &j : graph[i])
                if (dp[t - 1][j] != -1)
                    dp[t][i] = max(dp[t][i], dp[t - 1][j] + m[i]);

    int ans = 0;
    for (int t = 1; t <= 1000; t++)
        if (dp[t][0] != -1)
            ans = max(ans, dp[t][0] - C * t * t);
    cout << ans << '\n';

    return 0;
}