#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MAXM = 5e4 + 5;

int N, M;
int sz[MAXM];
vector<int> edg[MAXM];

void dfs(vector<vector<int>> &g, vector<bool> &visited, vector<int> &ts, int v) {
    visited[v] = true;
    for (auto &c : g[v])
        if (!visited[c])
            dfs(g, visited, ts, c);
    ts.push_back(v);
}

bool works(int m) {
    vector<vector<int>> g(N);
    for (int i = 0; i < m; i++)
        for (int j = 0; j < sz[i] - 1; j++)
            g[edg[i][j]].push_back(edg[i][j + 1]);

    vector<bool> visited(N);
    vector<int> ts;
    for (int i = 0; i < N; i++)
        if (!visited[i])
            dfs(g, visited, ts, i);
    reverse(ts.begin(), ts.end());

    vector<int> ind(N);
    for (int i = 0; i < N; i++) ind[ts[i]] = i;
    for (int i = 0; i < m; i++)
        for (int j = 0; j < sz[i] - 1; j++)
            if (ind[edg[i][j + 1]] <= ind[edg[i][j]]) return false;
    return true;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("milkorder.in", "r", stdin);
    freopen("milkorder.out", "w", stdout);

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        cin >> sz[i];
        for (int j = 0; j < sz[i]; j++) {
            int mi; cin >> mi;
            edg[i].push_back(mi - 1);
        }
    }

    int l = 0, r = M + 1;
    while (l + 1 < r) {
        int mid = (l + r) / 2;
        if (works(mid)) l = mid;
        else r = mid;
    }

    vector<vector<int>> g(N);
    vector<int> indeg(N);
    for (int i = 0; i < l; i++) {
        for (int j = 0; j < sz[i] - 1; j++) {
            g[edg[i][j]].push_back(edg[i][j + 1]);
            indeg[edg[i][j + 1]]++;
        }
    }

    set<int> q;
    for (int i = 0; i < N; i++)
        if (indeg[i] == 0)
            q.insert(i);

    vector<int> ts(N);
    for (int i = 0; i < N; i++) {
        int v = *q.begin(); q.erase(q.begin());
        ts[i] = v;
        for (auto &c : g[v]) {
            indeg[c]--;
            if (indeg[c] == 0) q.insert(c);
        }
    }

    for (int i = 0; i < N; i++) cout << ts[i] + 1 << (i == N - 1 ? "\n" : " ");
    
    return 0;
}