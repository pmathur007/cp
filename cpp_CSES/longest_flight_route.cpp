#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N, M;
vector<int> g[MAXN];
int dp[MAXN], c[MAXN];
bool visited[MAXN];

void dfs(int v) {
    dp[v] = (v == N ? 1 : 0);
    visited[v] = true;
    for (auto &u : g[v]) {
         if (!visited[u]) dfs(u);
         if (dp[u] > 0 && dp[u] + 1 > dp[v]) {
             dp[v] = dp[u] + 1;
             c[v] = u;
         }
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
    }

    for (int i = 1; i <= N; i++)
        if (!visited[i])
            dfs(i);

    if (dp[1] == 0) cout << "IMPOSSIBLE" << '\n';
    else {
        cout << dp[1] << '\n';
        int cur = 1;
        while (cur != 0) {
            cout << cur << (c[cur] == 0 ? "\n" :" ");
            cur = c[cur];
        }
    }

    return 0;
}