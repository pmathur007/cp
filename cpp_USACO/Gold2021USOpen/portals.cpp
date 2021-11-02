#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1e5 + 5;

int N;
pair<int, int> p[2 * MAXN];
vector<int> g[2 * MAXN];
bool visited[2 * MAXN];

// union find
int rt[2 * MAXN], sz[2 * MAXN];

int root(int v) {
    while (rt[v] != v) {
        rt[v] = rt[rt[v]];
        v = rt[v];
    }
    return v;
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

    cin >> N;

    //initialize union find
    for (int i = 1; i <= 2 * N; i++) {
        rt[i] = i;
        sz[i] = 1;
    }

    // create original graph
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    for (int i = 1; i <= N; i++) {
        int c, p1, p2, p3, p4; cin >> c >> p1 >> p2 >> p3 >> p4;
        pq.push({c, i});

        if (p[p1].f == 0) p[p1].f = i;
        else {
            p[p1].s = i;
            join(p[p1].f, p[p1].s);
        }

        if (p[p2].f == 0) p[p2].f = i;
        else {
            p[p2].s = i;
            join(p[p2].f, p[p2].s);
        }

        if (p[p3].f == 0) p[p3].f = i + N;
        else {
            p[p3].s = i + N;
            join(p[p3].f, p[p3].s);
        }

        if (p[p4].f == 0) p[p4].f = i + N;
        else {
            p[p4].s = i + N;
            join(p[p4].f, p[p4].s);
        }
    }

    int cost = 0;
    while (!pq.empty()) {
        pair<int, int> cur = pq.top(); pq.pop();
        if (root(cur.s) != root(cur.s + N)) {
            cost += cur.f;
            join(cur.s, cur.s + N);
        }
    }

    cout << cost << "\n";

    return 0;
}