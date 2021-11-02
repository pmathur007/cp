#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N, Q;
vector<int> g[MAXN];
int f[MAXN], s[MAXN];
long long a[MAXN], fwt[2 * MAXN];

void update(int i, long long v) {
    for (; i <= 2 * N; i += (i & -i)) fwt[i] += v;
}

long long query(int i) {
    long long ans = 0;
    for (; i > 0; i -= (i & -i)) ans += fwt[i];
    return ans;
}

int vi = 1;
void dfs(int v, int p) {
    f[v] = vi++;
    for (auto &c : g[v])
        if (c != p)
            dfs(c, v);
    s[v] = vi++;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 1; i <= N; i++) cin >> a[i];
    for (int i = 0; i < N - 1; i++) {
        int x, y; cin >> x >> y;
        g[x].push_back(y);
        g[y].push_back(x);
    }

    dfs(1, 0);
    for (int i = 1; i <= N; i++) {
        update(f[i], a[i]);
        update(s[i], a[i]);
    }

    for (int i = 0; i < Q; i++) {
        int ti; cin >> ti;
        if (ti == 1) {
            int si; long long xi; cin >> si >> xi;
            update(f[si], xi - a[si]);
            update(s[si], xi - a[si]);
            a[si] = xi;
        }
        else {
            int si; cin >> si;
            cout << (query(s[si]) - query(f[si] - 1)) / 2 << '\n';
        }
    }
    
    return 0;
}