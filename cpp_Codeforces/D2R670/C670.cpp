#include "bits/stdc++.h"
using namespace std;

void dfs(vector<vector<int>> &tree, vector<pair<int, int>> &sz, int p, int v)
{
    int size = 1;
    for (auto u : tree[v])
    {
        if (u != p)
            dfs(tree, sz, v, u);
        size += sz[u].first;
    }
    sz[v] = {size, v};
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--)
    {
        int N; cin >> N;
        vector<vector<int>> tree(N);
        for (int i = 0; i < N - 1; i++)
        {
            int x, y; cin >> x >> y;
            tree[x - 1].push_back(y - 1);
            tree[y - 1].push_back(x - 1);
        }

        vector<pair<int, int>> sz(N);
        dfs(tree, sz, -1, 0);
        sort(sz.begin(), sz.end());

        
    }

    return 0;
}