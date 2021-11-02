#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

void counting_sort(vector<int> &p, vector<int> &c)
{
    int n = p.size();
    vector<int> cnt(n);
    for (int i = 0; i < n; i++) cnt[c[i]]++;

    vector<int> pos(n);
    pos[0] = 0;
    for (int i = 1; i < n; i++) pos[i] = cnt[i - 1] + pos[i - 1];

    vector<int> p_new(n);
    for (int i = 0; i < n; i++)
    {
        p_new[pos[c[p[i]]]] = p[i];
        pos[c[p[i]]]++;
    }

    p = p_new;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string str;
    cin >> str;
    str += ' ';
    int n = str.size();

    vector<int> p(n), c(n);
    {
        vector<pair<int, int>> a(n);
        for (int i = 0; i < n; i++) a[i] = {str[i], i};
        sort(a.begin(), a.end());

        for (int i = 0; i < n; i++) p[i] = a[i].s;
        c[p[0]] = 0;
        for (int i = 1; i < n; i++) c[p[i]] = (str[p[i]] != str[p[i - 1]]) + c[p[i - 1]];
    }

    for (int k = 1; k < n; k <<= 1)
    {
        for (int i = 0; i < n; i++) p[i] = (p[i] - k + n) % n;
        counting_sort(p, c);

        vector<int> c_new(n);
        c_new[p[0]] = 0;
        for (int i = 1; i < n; i++) c_new[p[i]] = (c[p[i]] != c[p[i - 1]] || c[(p[i] + k) % n] != c[(p[i - 1] + k) % n]) + c_new[p[i - 1]];
        c = c_new;
    }

    vector<int> lcp(n);
    int k = 0;
    for (int i = 0; i < n - 1; i++)
    {
        int pi = c[i], j  = p[pi - 1];
        while (str[i + k] == str[j + k]) k++;
        lcp[pi] = k;
        k = max(k - 1, 0);
    }

    k = ((int) log2(n)) + 1;
    vector<vector<int>> st(k + 1, vector<int>(n));
    vector<int> log(n);
    log[1] = 0;

    for (int j = 0; j < n; j++)
    {
        st[0][j] = lcp[j];
        if (j >= 2)
            log[j] = log[j / 2] + 1;
    }

    for (int i = 1; i <= k; i++)
        for (int j = 0; j + (1 << i) <= n; j++)
            st[i][j] = min(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);

    int T;
    cin >> T;
    vector<pair<int, int>> ss(T);

    for (int t = 0; t < T; t++)
    {
        int l, r;
        cin >> l >> r;
        ss[t] = {l - 1, r - 1};
    }

    sort(ss.begin(), ss.end(), [&str, &c, &st, &log](pair<int, int> a, pair<int, int> b) -> bool {
        int l = min(c[a.f], c[b.f]) + 1, r = max(c[a.f], c[b.f]), sz = log[r - l + 1];
        int lcpn = l > r ? min(a.s - a.f, b.s - b.f) + 1 : min(st[sz][l], st[sz][r - (1 << sz) + 1]);

        if (a.f + lcpn > a.s && b.f + lcpn > b.s)
        {
            if (a.s - a.f == b.s - b.f) return (a.f == b.f ? a.s < b.s : a.f < b.f);
            return a.s - a.f < b.s - b.f;
        }
        if (a.f + lcpn > a.s)
            return true;
        if (b.f + lcpn > b.s)
            return false;
        return str[a.f + lcpn] < str[b.f + lcpn];
    });

    for (auto &pr : ss)
        cout << pr.f + 1 << ' ' << pr.s + 1 << '\n';

    return 0;
}