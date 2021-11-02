#include "bits/stdc++.h"
using namespace std;

#define MAX_N 100005
int N, Q;

int enjoy[MAX_N];
vector<int> graph[MAX_N];

int parent[MAX_N], heavy[MAX_N], depth[MAX_N];
int head[MAX_N], pos[MAX_N];
int stArr[MAX_N], segtree[4 * MAX_N];

void buildST(int l, int r, int v)
{
    if (l == r)
        segtree[v] = stArr[l];
    else
    {
        int mid = (l + r) / 2;
        buildST(l, mid, 2 * v);
        buildST(mid + 1, r, 2 * v + 1);
        segtree[v] = segtree[2 * v] ^ segtree[2 * v + 1];
    }
}

void updateST(int i, int l, int r, int v, int val)
{
    if (l == r)
        segtree[v] = val;
    else
    {
        int mid = (l + r) / 2;
        if (i <= mid)
            updateST(i, l, mid, 2 * v, val);
        else
            updateST(i, mid + 1, r, 2 * v + 1, val);
        segtree[v] = segtree[2 * v] ^ segtree[2 * v + 1];
    }
}

int queryST(int ql, int qr, int l, int r, int v)
{
    if (qr < l || r < ql)
        return 0;
    if (ql <= l && r <= qr)
        return segtree[v];
    int mid = (l + r) / 2;
    return queryST(ql, qr, l, mid, 2 * v) ^ queryST(ql, qr, mid + 1, r, 2 * v + 1);
}

int dfsHeavy(int v)
{
    int size = 1;
    int maxChildSize = 0;
    for (auto &c : graph[v])
    {
        if (parent[v] != c)
        {
            parent[c] = v;
            depth[c] = depth[v] + 1;
            int cSize = dfsHeavy(c);
            if (cSize > maxChildSize)
            {
                heavy[v] = c;
                maxChildSize = cSize;
            }
            size += cSize;
        }
    }
    return size;
}

int curPos = 1;
void decompose(int v, int h)
{
    head[v] = h;
    stArr[curPos] = enjoy[v];
    pos[v] = curPos++;
    if (heavy[v] != -1)
        decompose(heavy[v], h);
    for (auto &c : graph[v])
        if (c != parent[v] && c != heavy[v])
            decompose(c, c);
}

int treeQuery(int a, int b)
{
    int ans = 0;
    while (head[a] != head[b])
    {
        if (depth[head[a]] > depth[head[b]])
            swap(a, b);
        ans ^= queryST(pos[head[b]], pos[b], 1, N, 1);
        b = parent[head[b]];
    }
    if (depth[a] > depth[b])
        swap(a, b);
    ans ^= queryST(pos[a], pos[b], 1, N, 1);
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("cowland.in", "r", stdin);
    freopen("cowland.out", "w", stdout);

    cin >> N >> Q;

    for (int i = 1; i <= N; i++) cin >> enjoy[i];

    for (int i = 0; i < N - 1; i++)
    {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int i = 1; i <= N; i++)
    {
        parent[i] -1;
        heavy[i] = -1;
        depth[i] = 0;
    }

    dfsHeavy(1);
    decompose(1, 1);
    buildST(1, N, 1);

    for (int q = 0; q < Q; q++)
    {
        int t, i, j; cin >> t >> i >> j;
        if (t == 1)
            updateST(pos[i], 1, N, 1, j);
        else
            cout << treeQuery(i, j) << '\n';
    }

    return 0;
}