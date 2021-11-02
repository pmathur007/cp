#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N, Q;
int st[4 * MAXN];

void update(int i, int u, int l, int r, int v) {
    if (l == r) st[v] = u;
    else {
        int mid = (l + r) / 2;
        if (i <= mid) update(i, u, l, mid, 2 * v);
        else update(i, u, mid + 1, r, 2 * v + 1);
        st[v] = min(st[2 * v], st[2 * v + 1]);
    }
}

int query(int ql, int qr, int l, int r, int v) {
    if (r < ql || qr < l) return INT_MAX;
    if (ql <= l && r <= qr) return st[v];

    int mid = (l + r) / 2;
    return min(query(ql, qr, l, mid, 2 * v), query(ql, qr, mid + 1, r, 2 * v + 1));
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 1; i <= N; i++) {
        int x; cin >> x;
        update(i, x, 1, N, 1);
    }

    for (int i = 0; i < Q; i++) {
        int t; cin >> t;
        if (t == 1) {
            int k, u; cin >> k >> u;
            update(k, u, 1, N, 1);
        }
        else {
            int a, b; cin >> a >> b;
            cout << query(a, b, 1, N, 1) << '\n';
        }
    }
    
    return 0;
}