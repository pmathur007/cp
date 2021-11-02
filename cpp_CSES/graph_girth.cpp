#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2505;

int N, M;
vector<int> g[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 1; i <= M; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    int minC = INT_MAX;
    for (int n = 1; n <= N; n++) {
        vector<int> d(N + 1, -1);
        queue<int> q;
        q.push(n);
        d[n] = 0;
        while (!q.empty()) {
            int v = q.front(); q.pop();
            for (auto &c : g[v]) {
                if (d[c] >= d[v])
                    minC = min(minC, d[c] + d[v] + 1);
                if (d[c] == -1) {
                    d[c] = d[v] + 1;
                    q.push(c);
                }
            }
        }
    }

    cout << (minC == INT_MAX ? -1 : minC) << '\n';
}