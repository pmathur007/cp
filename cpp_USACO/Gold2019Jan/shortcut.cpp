#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAX_N = 10005;

int N, M, T;
vector<pair<int, int>> graph[MAX_N];

int c[MAX_N], par[MAX_N];
long long dist[MAX_N], numCows[MAX_N];
bool visited[MAX_N];
vector<int> dfsGraph[MAX_N];

void dfs(int v)
{
    numCows[v] = c[v];
    for (auto &u : dfsGraph[v])
    {
        dfs(u);
        numCows[v] += numCows[u];
    }
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("shortcut.in", "r", stdin);
    freopen("shortcut.out", "w", stdout);

    cin >> N >> M >> T;
    for (int i = 1; i <= N; i++) cin >> c[i];

    for (int i = 0; i < M; i++)
    {
        int a, b, t; cin >> a >> b >> t;
        graph[a].emplace_back(b, t);
        graph[b].emplace_back(a, t);
    }

    fill(dist, dist + N + 1, LONG_LONG_MAX);
    dist[1] = 0;
    fill(par, par + N + 1, -1);
    fill(visited, visited + N + 1, false);

    set<pair<long long, int>> q;
    q.insert({0, 1});
    while (!q.empty())
    {
        pair<int, int> v = *q.begin();
        q.erase(q.begin());

        if (visited[v.s]) continue;
        visited[v.s] = true;
        dist[v.s] = v.f;

        for (auto &u : graph[v.s])
        {
            if (v.f + u.s < dist[u.f] || (v.f + u.s == dist[u.f] && v.s < par[u.f]))
            {
                dist[u.f] = v.f + u.s;
                par[u.f] = v.s;
                if (!visited[u.f])
                    q.insert({dist[u.f], u.f});
            }
        }
    }

    for (int i = 1; i <= N; i++)
        dfsGraph[par[i]].push_back(i);
    dfs(1);

    long long ans = 0;
    for (int i = 1; i <= N; i++)
        ans = max(ans, numCows[i] * (dist[i] - T));
    cout << ans << '\n';

    return 0;
}