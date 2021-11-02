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

int search_l(vector<int> &p, string &str, string &s_i)
{
    int n = str.size(), sn = s_i.size(), l = 0, r = n;
    while (l < r)
    {
        int mid = (l + r) / 2;
        if (s_i.compare(str.substr(p[mid], sn)) > 0)
            l = mid + 1;
        else
            r = mid;
    }
    return l;
}

int search_r(vector<int> &p, string &str, string &s_i)
{
    int n = str.size(), sn = s_i.size(), l = 0, r = n;
    while (l < r)
    {
        int mid = (l + r) / 2;
        if (s_i.compare(str.substr(p[mid], sn)) < 0)
            r = mid;
        else
            l = mid + 1;
    }
    return l;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string str;
    cin >> str;
    str += '$';
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

    int T;
    cin >> T;
    for (int t = 0; t < T; t++)
    {
        string s_i;
        cin >> s_i;
        cout << search_r(p, str, s_i) - search_l(p, str, s_i) << '\n';
    }

    return 0;
}