#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

void counting_sort(int n, vector<int> &p, vector<int> &c)
{
    vector<int> cnt(n);
    for (int i = 0; i < n; i++) cnt[c[i]]++;

    vector<int> pos(n);
    pos[0] = 0;
    for (int i = 1; i < n; i++) pos[i] = pos[i - 1] + cnt[i - 1];

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

    string s1, s2;
    cin >> s1 >> s2;
    string str = s1 + "$" + s2 + "#";

    int n = str.size();
    int sn = s1.size();

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
        counting_sort(n, p, c);

        vector<int> c_new(n);
        c_new[p[0]] = 0;
        for (int i = 1; i < n; i++) c_new[p[i]] = c_new[p[i - 1]] + (c[p[i]] != c[p[i - 1]] || c[(p[i] + k) % n] != c[(p[i - 1] + k) % n]);
        c = c_new;
    }

    string ans;
    int ansn = 0;

    vector<int> lcp(n);
    int k = 0;
    for (int i = 0; i < n - 1; i++)
    {
        int pi = c[i], j = p[pi - 1];
        while (str[i + k] == str[j + k]) k++;
        lcp[pi] = k;
        k = max(0, k - 1);

        if (((i < sn && j >= sn) || (i >= sn && j < sn)) && (lcp[pi] >= ansn))
        {
            string pot_ans = str.substr(i, lcp[pi]);
            if (lcp[pi] == ansn) ans = ans.compare(pot_ans) < 0 ? ans : pot_ans;
            else
            {
                ans = pot_ans;
                ansn = lcp[pi];
            }
        }
    }

    cout << ans << '\n';

    return 0;
}