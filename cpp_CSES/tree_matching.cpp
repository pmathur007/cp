#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N;
vector<int> g[MAXN];
int dp1[MAXN], dp2[MAXN];

void dfs(int v, int p) {
    for (auto &c : g[v]) {
        if (c != p) {
            dfs(c, v);
            dp2[v] += max(dp1[c], dp2[c]);
        }
    }
    for (auto &c : g[v])
        if (c != p)
            dp1[v] = max(dp1[v], dp2[c] + 1 + dp2[v] - max(dp1[c], dp2[c]));
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N - 1; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    dfs(1, 0);
    cout << max(dp1[1], dp2[1]) << '\n';
    
    return 0;
}