#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N, Q;
long long a[MAXN], fwt[MAXN];

void update(int i, long long v) {
    for (; i <= N; i += (i & -i)) fwt[i] += v;
}

long long query(int i) {
    long long sum = 0;
    for (; i > 0; i -= (i & -i)) sum += fwt[i];
    return sum;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 1; i <= N; i++) {
        cin >> a[i];
        update(i, a[i]);
    }

    for (int i = 0; i < Q; i++) {
        int t; cin >> t;
        if (t == 1) {
            int k; long long u; cin >> k >> u;
            update(k, u - a[k]);
            a[k] = u;
        }
        else {
            int ai, bi; cin >> ai >> bi;
            cout << query(bi) - query(ai - 1) << '\n';
        }
    }
    
    return 0;
}