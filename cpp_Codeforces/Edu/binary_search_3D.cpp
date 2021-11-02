#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

int N, M, D;
vector<pair<int, int>> graph[100005];

int shortestPath(int m)
{
    set<pair<int, int>> q;
    vector<bool> visited(N);

    q.insert({0, 0});
    visited[0] = true;
    while (!q.empty())
    {
        pair<int, int> v = *q.begin();
        q.erase(q.begin());

        if (v.s == N - 1)
            return v.f;
        for (auto &u : graph[v.s])
        {
            if (!visited[u.f] && u.s <= m)
            {
                q.insert({v.f + 1, u.f});
                visited[u.f] = true;
            }
        }
    }
    return INT_MAX;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M >> D;
    for (int i = 0; i < M; i++)
    {
        int a, b, c; cin >> a >> b >> c;
        graph[a - 1].emplace_back(b - 1, c);
    }

    int l = 0, r = 1e9 + 5;
    while (l + 1 < r)
    {
        int mid = (l + r) / 2;
        if (shortestPath(mid) <= D) r = mid;
        else l = mid;
    }

    set<pair<int, int>> q;
    vector<bool> visited(N);
    vector<int> p(N);
    stack<int> path;

    q.insert({0, 0});
    visited[0] = true;
    p[0] = -1;
    while (!q.empty())
    {
        pair<int, int> v = *q.begin();
        q.erase(q.begin());

        if (v.s == N - 1)
        {
            int cur = v.s;
            while (cur != -1)
            {
                path.push(cur);
                cur = p[cur];
            }
        }
        for (auto &u : graph[v.s])
        {
            if (!visited[u.f] && u.s <= r)
            {
                q.insert({v.f + 1, u.f});
                visited[u.f] = true;
                p[u.f] = v.s;
            }
        }
    }

    if (path.empty() || path.size() - 1 > D)
        cout << -1 << '\n';
    else
    {
        cout << path.size() - 1 << '\n';
        while (!path.empty())
        {
            cout << path.top() + 1 << (path.top() == N - 1 ? '\n' : ' ');
            path.pop();
        }
    }

    return 0;
}