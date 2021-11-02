#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N, Q;
vector<int> g[MAXN];
int idx[MAXN], h[MAXN], trav[2 * MAXN], st[8 * MAXN];

void buildST(int l, int r, int v) {
    if (l == r) st[v] = trav[l];
    else {
        int mid = (l + r) / 2;
        buildST(l, mid, 2 * v);
        buildST(mid + 1, r, 2 * v + 1);
        st[v] = h[st[2 * v]] < h[st[2 * v + 1]] ? st[2 * v] : st[2 * v + 1];
    }
}

int queryST(int ql, int qr, int l, int r, int v) {
    if (r < ql || qr < l) return -1;
    if (ql <= l && r <= qr) return st[v];

    int mid = (l + r) / 2;
    int left = queryST(ql, qr, l, mid, 2 * v);
    int right = queryST(ql, qr, mid + 1, r, 2 * v + 1);
    if (left == -1) return right;
    if (right == - 1) return left;
    return h[left] < h[right] ? left : right;
}

int vi = 1;
void dfs(int v) {
    idx[v] = vi;
    trav[vi++] = v;
    for (auto &c : g[v]) {
        h[c] = h[v] + 1;
        dfs(c);
        trav[vi++] = v;
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 2; i <= N; i++) {
        int e; cin >> e;
        g[e].push_back(i);
    }

    h[1] = 1;
    dfs(1);

    buildST(1, 2 * N - 1, 1);

    for (int i = 0; i < Q; i++) {
        int a, b; cin >> a >> b;
        int ai = idx[a], bi = idx[b];
        if (ai > bi) swap(ai, bi);
        cout << queryST(ai, bi, 1, 2 * N - 1, 1) << '\n';
    }
    
    return 0;
}