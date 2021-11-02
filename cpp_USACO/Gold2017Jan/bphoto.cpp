#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N;
int fwt[MAXN];

void update(int i) {
    for (; i <= N; i += (i & -i)) fwt[i]++;
}

int query(int i) {
    int sum = 0;
    for (; i > 0; i -= (i & -i)) sum += fwt[i];
    return sum;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("bphoto.in", "r", stdin);
    freopen("bphoto.out", "w", stdout);

    cin >> N;
    priority_queue<pair<int, int>> pq;
    for (int i = 1; i <= N; i++) {
        int h; cin >> h;
        pq.push({h, i});
    }

    int unbalanced = 0;
    for (int i = 0; i < N; i++) {
        pair<int, int> p = pq.top(); pq.pop();
        int q = query(p.second);
        update(p.second);
        int s = min(q, i - q), l = max(q, i - q);
        if (l > 2 * s) unbalanced++;
    }

    cout << unbalanced << '\n';
    
    return 0;
}