#include "bits/stdc++.h"
using namespace std;

const int MAXN = 100005;
int N;
int a[MAXN];

#define f first
#define s second

void update(int i, int v)
{
    for (; i <= N; i += (i & -i)) a[i] += v;
}

int query(int i)
{
    int sum = 0;
    for (; i > 0; i -= (i & -i)) sum += a[i];
    return sum;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("haircut.in", "r", stdin);
    freopen("haircut.out", "w", stdout);

    cin >> N;
    for (int i = 1; i <= N; i++) update(i, 1);

    set<pair<int, int>> q;
    for (int i = 1; i <= N; i++)
    {
        int ai; cin >> ai;
        q.insert({ai, i});
    }

    long ans = 0;
    for (int j = 0; j < N; j++)
    {
        cout << ans << '\n';
        while (!q.empty() && q.begin()->f <= j)
        {
            pair<int, int> p = *q.begin();
            q.erase(q.begin());
            update(p.s, -1);
            ans += query(p.s - 1);
        }
    }

    return 0;
}