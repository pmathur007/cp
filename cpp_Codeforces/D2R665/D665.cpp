#include "bits/stdc++.h"
using namespace std;

void dfs(vector<vector<int>> &graph, vector<long long> &sz, int p, int v)
{
    sz[v] = 1;
    for (auto &u : graph[v])
    {
        if (u != p)
        {
            dfs(graph, sz, v, u);
            sz[v] += sz[u];
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    long long MOD = 1e9 + 7;
    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int N;
        cin >> N;
        vector<vector<int>> graph(N);

        for (int i = 0; i < N - 1; i++)
        {
            int a, b;
            cin >> a >> b;
            graph[a - 1].push_back(b - 1);
            graph[b - 1].push_back(a - 1);
        }

        vector<long long> sz(N);
        dfs(graph, sz, -1, 0);

        sort(sz.begin(), sz.end(), [N](long long a, long long b)
        {
            return (a * (N - a)) < (b * (N - b));
        });

        int M;
        cin >> M;
        vector<long long> k(M);
        for (int i = 0; i < M; i++) cin >> k[i];
        sort(k.begin(), k.end());

        long long ans = 0;
        if (M <= N - 1)
        {
            for (int si = N - 1, ki = M - 1; si > 0; si--, ki--)
            {
                ans = (ans + (((sz[si] * (N - sz[si])) % MOD) * (ki >= 0 ? k[ki] : 1)) % MOD) % MOD;
            }
        }
        else
        {
            for (int si = 1, ki = 0; si < N; si++, ki++)
            {
                if (si == N - 1)
                {
                    long long temp = (sz[si] * (N - sz[si])) % MOD;
                    for (; ki < M; ki++)
                        temp = (temp * k[ki]) % MOD;
                    ans = (ans + temp) % MOD;
                }
                else
                    ans = (ans + (((sz[si] * (N - sz[si])) % MOD) * k[ki]) % MOD) % MOD;
            }
        }
        cout << ans << '\n';
    }

    return 0;
}