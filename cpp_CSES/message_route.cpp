#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1e5 + 5;

int N, M;
vector<int> g[MAXN];
int p[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 1; i <= M; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    set<pair<int, int>> q;
    q.insert({0, 1});
    bool found = false;
    while (!q.empty()) {
        pair<int, int> v = *q.begin();
        q.erase(q.begin());

        if (v.s == N) {
            found = true;
            cout << v.f + 1 << '\n';
            vector<int> path;
            int cur = v.s;
            while (cur != 0) {
                path.push_back(cur);
                cur = p[cur];
            }
            for (int i = path.size() - 1; i >= 0; i--) cout << path[i] << (i == 0 ? '\n' : ' ');
            break;
        }

        for (auto &c : g[v.s]) {
            if (c != 1 && p[c] == 0) {
                p[c] = v.s;
                q.insert({v.f + 1, c});
            }
        }
    }

    if (!found)
        cout << "IMPOSSIBLE" << '\n';
    
    return 0;
}