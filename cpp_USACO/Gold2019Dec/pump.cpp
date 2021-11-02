#include "bits/stdc++.h"
using namespace std;

#define w first
#define n second

struct edge {
    int d, c, f;
};

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("pump.in", "r", stdin);
    freopen("pump.out", "w", stdout);

    int N, M; cin >> N >> M;
    vector<vector<edge>> graph(N, vector<edge>());
    for (int i = 0; i < M; i++)
    {
        int a, b, c, f; cin >> a >> b >> c >> f;
        graph[a - 1].push_back({b - 1, c, f});
        graph[b - 1].push_back({a - 1, c, f});
    }

    double ans = 0;
    for (int minf = 1; minf <= 1000; minf++)
    {
        set<pair<int, int>> q;
        vector<int> dist(N, INT_MAX);
        vector<bool> visited(N, false);

        q.insert({0, 0});
        while (!q.empty())
        {
            pair<int, int> v = *q.begin();
            q.erase(q.begin());

            if (visited[v.n]) continue;
            visited[v.n] = true;
            dist[v.n] = v.w;

            for (auto &e : graph[v.n])
            {
                if (e.f >= minf && v.w + e.c < dist[e.d])
                {
                    dist[e.d] = v.w + e.c;
                    if (!visited[e.d])
                        q.insert({dist[e.d], e.d});
                }
            }
        }

        if (dist[N - 1] != INT_MAX)
            ans = max(ans, ((double) minf) / dist[N - 1]);
    }

    cout << (int) (ans * 1000000) << '\n';

    return 0;
}