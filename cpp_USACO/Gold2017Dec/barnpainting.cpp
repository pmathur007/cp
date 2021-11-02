#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MOD = 1e9 + 7;

int N, K;
vector<int> g[MAXN];
int color[MAXN];
long long dp[MAXN][3];

void dfs(int v, int p) {
    for (auto &c : g[v]) if (c != p) dfs(c, v);
    for (int i = 0; i < 3; i++) {
        if (color[v] >= 0 && color[v] != i) dp[v][i] = 0;
        else {
            dp[v][i] = 1;
            for (auto &c : g[v])
                if (c != p)
                    dp[v][i] = (dp[v][i] * ((dp[c][(i + 1) % 3] + dp[c][(i + 2) % 3]) % MOD)) % MOD;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("barnpainting.in", "r", stdin);
    freopen("barnpainting.out", "w", stdout);

    cin >> N >> K;
    for (int i = 0; i < N - 1; i++) {
        int x, y; cin >> x >> y;
        g[x].push_back(y);
        g[y].push_back(x);
    }

    fill(color, color + N + 1, -1);
    for (int i = 0; i < K; i++) {
        int b, c; cin >> b >> c;
        if (color[b] != -1) {
            cout << 0 << '\n';
            exit(0);
        }
        color[b] = c - 1;
    }

    dfs(1, 0);
    cout << (dp[1][0] + dp[1][1] + dp[1][2]) % MOD<< '\n';
    
    return 0;
}