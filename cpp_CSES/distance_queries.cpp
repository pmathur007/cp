#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N, Q;
vector<int> g[MAXN];
int idx[MAXN], h[MAXN], trav[2 * MAXN], st[8 * MAXN];

void buildST(int l, int r, int v) {
    if (l == r) st[v] = h[trav[l]];
    else {
        int mid = (l + r) / 2;
        buildST(l, mid, 2 * v);
        buildST(mid + 1, r, 2 * v + 1);
        st[v] = min(st[2 * v], st[2 * v + 1]);
    }
}

int queryST(int ql, int qr, int l, int r, int v) {
    if (r < ql || qr < l) return INT_MAX;
    if (ql <= l && r <= qr) return st[v];

    int mid = (l + r) / 2;
    int left = queryST(ql, qr, l, mid, 2 * v);
    int right = queryST(ql, qr, mid + 1, r, 2 * v + 1);
    return min(left, right);
}

int vi = 1;
void dfs(int v, int p) {
    idx[v] = vi;
    trav[vi++] = v;
    for (auto &c : g[v]) {
        if (c != p) {
            h[c] = h[v] + 1;
            dfs(c, v);
            trav[vi++] = v;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 0; i < N - 1; i++) {
        int a, b; cin >> a >> b;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    h[1] = 1;
    dfs(1, 0);
    buildST(1, 2 * N - 1, 1);

    for (int i = 0; i < Q; i++) {
        int a, b; cin >> a >> b;
        if (idx[a] > idx[b]) swap(a, b);
        int minh = queryST(idx[a], idx[b], 1, 2 * N - 1, 1);
        cout << (h[a] - minh) + (h[b] - minh) << '\n';
    }
    
    return 0;
}