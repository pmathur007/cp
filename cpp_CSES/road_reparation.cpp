#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1e5 + 5;

int N, M;
int arr[MAXN], sz[MAXN];

int root(int v) {
    while (arr[v] != v) {
        arr[v] = arr[arr[v]];
        v = arr[v];
    }
    return v;
}

void join(int a, int b) {
    int ra = root(a), rb = root(b);
    if (sz[ra] < sz[rb]) {
        arr[ra] = arr[rb];
        sz[rb] += sz[ra];
    }
    else {
        arr[rb] = arr[ra];
        sz[ra] += sz[rb];
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    multiset<pair<int, pair<int, int>>> q;
    for (int i = 0; i < M; i++) {
        int a, b, c; cin >> a >> b >> c;
        q.insert({c, {a, b}});
    }

    for (int i = 1; i <= N; i++) arr[i] = i, sz[i] = 1;

    long long ans = 0;
    int comp = N;
    while (!q.empty()) {
        pair<int, pair<int, int>> e = *q.begin(); q.erase(q.begin());
        if (root(e.s.f) != root(e.s.s)) {
            ans += e.f;
            join(e.s.f, e.s.s);
            comp--;
        }
    }

    if (comp == 1) cout << ans << '\n';
    else cout << "IMPOSSIBLE" << '\n';
    
    return 0;
}