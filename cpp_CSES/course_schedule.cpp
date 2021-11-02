#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N, M;
vector<int> g[MAXN];
bool in[MAXN], visited[MAXN];
vector<int> ts;

void dfs(int v) {
    visited[v] = true;
    for (auto &c : g[v]) {
        if (!visited[c]) {
            visited[c] = true;
            dfs(c);
        }
    }
    ts.push_back(v);
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
    }

    for (int n = 1; n <= N; n++) {
        if (!visited[n]) {
            visited[n] = true;
            dfs(n);
        }
    }
    reverse(ts.begin(), ts.end());

    vector<int> ind(N + 1);
    for (int i = 0; i < N; i++) ind[ts[i]] = i;
    for (int i = 1; i <= N; i++) {
        for (auto &j : g[i]) {
            if (ind[j] <= ind[i]) {
                cout << "IMPOSSIBLE\n";
                exit(0);
            }
        }
    }
    for (int i = 0; i < N; i++) cout << ts[i] << (i == N - 1 ? "\n" : " ");
    
    return 0;
}