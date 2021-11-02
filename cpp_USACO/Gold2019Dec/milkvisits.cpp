#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

int N, M;
vector<int> graph[100005];
int segtree[800005];
int heavy[100005], d[100005], p[100005];
int head[100005], pos[100005];
int posi = 1;

void buildST(int l, int r, int idx)
{
    if (l == r)
        segtree[idx] = 0;
    else
    {
        int mid = (l + r) / 2;
        buildST(l, mid, 2 * idx);
        buildST(mid + 1, r, 2 * idx + 1);
        segtree[idx] = max(segtree[2 * idx], segtree[2 * idx + 1]);
    }
}

void updateST(int i, int v, int l, int r, int idx)
{
    if (l == r)
        segtree[idx] = v;
    else
    {
        int mid = (l + r) / 2;
        if (i <= mid)
            updateST(i, v, l, mid, 2 * idx);
        else
            updateST(i, v, mid + 1, r, 2 * idx + 1);
        segtree[idx] = max(segtree[2 * idx], segtree[2 * idx + 1]);
    }
}

int queryST(int ql, int qr, int l, int r, int idx)
{
    if (ql <= l && r <= qr) return segtree[idx];
    if (r < ql || l > qr) return 0;
    int mid = (l + r) / 2;
    return max(queryST(ql, qr, l, mid, 2 * idx), queryST(ql, qr, mid + 1, r, 2 * idx + 1));
}

int labelHeavy(int v)
{
    int sz = 1;
    int heavycsz = 0;
    for (auto &c : graph[v])
    {
        if (c != p[v])
        {
            p[c] = v;
            d[c] = d[v] + 1;
            int csz = labelHeavy(c);
            if (csz > heavycsz)
            {
                heavy[v] = c;
                heavycsz = csz;
            }
            sz += csz;
        }
    }
    return sz;
}

void decompose(int v, int h)
{
    head[v] = h;
    pos[v] = posi++;
    if (heavy[v] != 0)
        decompose(heavy[v], h);
    for (auto &c : graph[v])
        if (c != p[v] && c != heavy[v])
            decompose(c, c);
}

int queryTree(int a, int b)
{
    int ans = 0;
    for (; head[a] != head[b]; a = p[head[a]])
    {
        if (d[head[a]] < d[head[b]]) swap(a, b);
        ans = max(ans, queryST(pos[head[a]], pos[a], 1, N, 1));
    }
    if (d[a] < d[b]) swap(a, b);
    ans = max(ans, queryST(pos[b], pos[a], 1, N, 1));
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("milkvisits.in", "r", stdin);
    freopen("milkvisits.out", "w", stdout);

    cin >> N >> M;
    multiset<pair<int, int>> nodeq;
    for (int i = 1; i <= N; i++)
    {
        int t; cin >> t;
        nodeq.insert({t, i});
    }

    for (int i = 0; i < N - 1; i++)
    {
        int x, y; cin >> x >> y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    fill(heavy, heavy + N, 0);
    fill(d, d + N, 0);
    fill(p, p + N, 0);

    labelHeavy(1);
    decompose(1, 1);
    buildST(1, N, 1);

    multiset<pair<int, pair<int, pair<int, int>>>> queryq;
    vector<bool> ans(M);
    for (int i = 0; i < M; i++)
    {
        int a, b, c; cin >> a >> b >> c;
        queryq.insert({c, {i, {a, b}}});
    }

    while (!queryq.empty())
    {
        auto query = *queryq.begin();
        queryq.erase(queryq.begin());

        while (!nodeq.empty() && nodeq.begin()->f <= query.f)
        {
            updateST(pos[nodeq.begin()->s], nodeq.begin()->f, 1, N, 1);
            nodeq.erase(nodeq.begin());
        }

        ans[query.s.f] = queryTree(query.s.s.f, query.s.s.s) == query.f;
    }

    for (int i = 0; i < M; i++) cout << ans[i];
    cout << '\n';

    return 0;
}