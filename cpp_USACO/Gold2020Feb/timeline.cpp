#include "bits/stdc++.h"
using namespace std;

#define d first
#define w second

int N, M, C;
int s[100005];
vector<pair<int, int>> graph[100005];
bool visited[100005];

void dfs(int v)
{
    visited[v] = true;
    for (auto e : graph[v])
    {
        if (!visited[e.d])
            dfs(e.d);
        s[v] = max(s[v], s[e.d] + e.w);
    }
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("timeline.in", "r", stdin);
    freopen("timeline.out", "w", stdout);

    cin >> N >> M >> C;
    for (int i = 0; i < N; i++)
    {
        cin >> s[i];
        visited[i] = false;
    }

    for (int i = 0; i < C; i++)
    {
        int a, b, x; cin >> a >> b >> x;
        graph[b - 1].emplace_back(a - 1, x);
    }

    for (int i = 0; i < N; i++)
        if (!visited[i])
            dfs(i);

    for (int i = 0; i < N; i++) cout << s[i] << '\n';

    return 0;
}