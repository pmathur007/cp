#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXNQ = 1e5 + 5;

int N, Q;
vector<pair<int, int>> g[MAXNQ];
int rt[MAXNQ], sz[MAXNQ], ans[MAXNQ];

int root(int a) {
    while (rt[a] != a) {
        rt[a] = rt[rt[a]];
        a = rt[a];
    }
    return a;
}

void join(int a, int b) {
    int ra = root(a), rb = root(b);
    if (sz[ra] < sz[rb]) {
        rt[ra] = rt[rb];
        sz[rb] += sz[ra];
    }
    else {
        rt[rb] = rt[ra];
        sz[ra] += sz[rb];
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("mootube.in", "r", stdin);
    freopen("mootube.out", "w", stdout);

    cin >> N >> Q;
    multiset<pair<int, pair<int, int>>, greater<pair<int, pair<int, int>>>> eq, qq;
    for (int i = 0; i < N - 1; i++) {
        int p, q, r; cin >> p >> q >> r;
        eq.insert({r, {p, q}});
    }
    for (int i = 0; i < Q; i++) {
        int k, v; cin >> k >> v;
        qq.insert({k, {v, i}});
    }

    for (int i = 1; i <= N; i++) rt[i] = i, sz[i] = 1;
    while (!qq.empty()) {
        pair<int, pair<int, int>> q = *qq.begin(); qq.erase(qq.begin());
        while (!eq.empty() && eq.begin()->f >= q.f) {
            pair<int, pair<int, int>> e = *eq.begin(); eq.erase(eq.begin());
            join(e.s.f, e.s.s);
        }
        ans[q.s.s] = sz[root(q.s.f)] - 1;
    }

    for (int i = 0; i < Q; i++) cout << ans[i] << "\n";

    return 0;
}