#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1e5 + 5;
const long long MAXD = 1e15;

int N, M;
vector<pair<int, long long>> g[MAXN];
bool visited[MAXN];
long long dist[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int a, b, c; cin >> a >> b >> c;
        g[a].emplace_back(b, c);
    }

    using T = pair<long long, int>; priority_queue<T, vector<T>, greater<T>> q;
    fill(dist, dist + N + 1, MAXD);
    dist[1] = 0;
    q.push({0, 1});
    while (!q.empty()) {
        pair<long long, int> v = q.top(); q.pop();

        if (visited[v.s]) continue;
        visited[v.s] = true;

        for (auto &e : g[v.s]) {
            if (!visited[e.f] && dist[v.s] + e.s < dist[e.f]) {
                dist[e.f] = dist[v.s] + e.s;
                q.push({dist[v.s] + e.s, e.f});
            }
        }
    }

    for (int i = 1; i <= N; i++) cout << dist[i] << (i == N ? "\n" : " ");

    return 0;
}