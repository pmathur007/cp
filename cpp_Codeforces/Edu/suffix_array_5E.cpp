#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

void countingSort(vector<int> &p, vector<int> &c)
{
    int n = p.size();
    vector<int> cnt(n);
    for (int i = 0; i < n; i++) cnt[c[i]]++;

    vector<int> pos(n);
    pos[0] = 0;
    for (int i = 1; i < n; i++) pos[i] = pos[i - 1] + cnt[i - 1];

    vector<int> p_new(n);
    for (auto &pv : p)
    {
        p_new[pos[c[pv]]] = pv;
        pos[c[pv]]++;
    }
    p = p_new;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("/Users/pranav/Documents/School/TJHSST/CP/test.txt", "r", stdin);

    int n, m; cin >> n >> m;
    vector<int> str(n + 1);
    for (int i = 0; i < n; i++)
    {
        int v; cin >> v;
        str[i] = v;
    }
    str[n] = -1;
    n++;

    vector<int> p(n), c(n);
    {
        vector<pair<int, int>> a(n);
        for (int i = 0; i < n; i++) a[i] = {str[i], i};
        sort(a.begin(), a.end());

        for (int i = 0; i < n; i++) p[i] = a[i].s;
        c[p[0]] = 0;
        for (int i = 1; i < n; i++) c[p[i]] = c[p[i - 1]] + (str[p[i]] != str[p[i - 1]]);
    }

    for (int k = 1; k < n; k <<= 1)
    {
        for (int i = 0; i < n; i++) p[i] = (p[i] - k + n) % n;
        countingSort(p, c);

        vector<int> c_new(n);
        c_new[p[0]] = 0;
        for (int i = 1; i < n; i++) c_new[p[i]] = c_new[p[i - 1]] + (c[p[i]] != c[p[i - 1]] || c[(p[i] + k) % n] != c[(p[i - 1] + k) % n]);
        c = c_new;
    }

    vector<int> lcp(n);
    int k = 0;
    for (int i = 0; i < n - 1; i++)
    {
        int pi = c[i], j = p[pi - 1];
        while (str[i + k] == str[j + k]) k++;
        lcp[pi] = k;
        k = max(k - 1, 0);
    }

    stack<pair<int, int>> s1;
    long long ans = n - 1;
    int ansl = n - 1, ansi = 0;
    for (int i = 0; i < n; i++)
    {
        while (!s1.empty() && s1.top().f > lcp[i])
        {
            pair<int, int> t = s1.top();
            if (((long long) t.f) * (i - t.s + 1) > ans)
            {
                ans = ((long long) t.f) * (i - t.s + 1);
                ansl = t.f;
                ansi = p[t.s];
            }
            s1.pop();
        }
        if (lcp[i] > (s1.empty() ? 0 : s1.top().f))
            s1.push({lcp[i], i});
    }
    while (!s1.empty())
    {
        pair<int, int> t = s1.top();
        if (((long long) t.f) * (n - t.s + 1) > ans)
        {
            ans = ((long long) t.f) * (n - t.s + 1);
            ansl = t.f;
            ansi = p[t.s];
        }
        s1.pop();
    }

    cout << ans << '\n';
    cout << ansl << '\n';
    for (int i = ansi; i < ansi + ansl; i++) cout << str[i] << (i == ansi + ansl - 1 ? '\n' : ' ');

    return 0;
}