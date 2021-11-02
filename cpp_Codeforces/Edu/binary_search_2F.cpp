#include "bits/stdc++.h"
using namespace std;

string t, p;
int tn, pn;
int a[200005];

bool good(int v)
{
    vector<bool> del(tn);
    for (int i = 0; i < v; i++)
        del[a[i] - 1] = true;

    int pi = 0;
    for (int i = 0; pi < pn && i < tn; i++)
        if (!del[i] && t[i] == p[pi]) pi++;
    return pi == pn;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> t; cin >> p;
    tn = t.size(), pn = p.size();
    for (int i = 0; i < tn; i++) cin >> a[i];

    int l = 0; int r = tn + 1;
    while (l + 1 < r)
    {
        int mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    cout << l << '\n';

    return 0;
}