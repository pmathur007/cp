#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MOD = 1e9 + 7;

int N, M;
vector<int> g[MAXN];
long dp[MAXN];
bool visited[MAXN];

void dfs(int v) {
    if (v == N) dp[v] = 1;
    visited[v] = true;
    for (auto &c : g[v]) {
        if (!visited[c]) dfs(c);
        dp[v] = (dp[v] + dp[c]) % MOD;
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
    }

    dfs(1);
    cout << dp[1] << '\n';
    
    return 0;
}