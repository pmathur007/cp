#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5+5;

int N, Q;
vector<int> g[MAXN];
int d[MAXN], loc[MAXN], stArr[2*MAXN], st[8 * MAXN];

void buildST(int l, int r, int idx) {
    if (l == r) st[idx] = stArr[l];
    else {
        int mid = (l + r) / 2;
        buildST(l, mid, 2*idx);
        buildST(mid + 1, r, 2*idx+1);
        st[idx] = d[st[2*idx]] < d[st[2*idx+1]] ? st[2*idx] : st[2*idx+1];
    }
}

int queryST(int ql, int qr, int l, int r, int idx) {
    if (r < ql || qr < l) return -1;
    if (ql <= l && r <= qr) return st[idx];

    int mid = (l + r) / 2;
    int lq = queryST(ql, qr, l, mid, 2*idx), rq = queryST(ql, qr, mid + 1, r, 2*idx+1);
    if (lq == -1) return rq;
    if (rq == -1) return lq;
    return d[lq] < d[rq] ? lq : rq;
}

int pos = 1;
void dfs(int v) {
    loc[v] = pos;
    stArr[pos++] = v;
    for (auto &c : g[v]) {
        d[c] = d[v] + 1;
        dfs(c);
        stArr[pos++] = v;
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 2; i <= N; i++) {
        int ei; cin >> ei;
        g[ei].push_back(i);
    }

    dfs(1);
    buildST(1, 2*N-1, 1);

    for (int i = 0; i < Q; i++) {
        int a, b; cin >> a >> b;
        cout << queryST(min(loc[a], loc[b]), max(loc[a], loc[b]), 1, 2*N-1, 1) << "\n";
    }

    return 0;
}