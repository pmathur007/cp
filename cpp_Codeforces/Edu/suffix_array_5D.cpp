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

    string str;
    cin >> str;
    str += '$';
    int n = str.size();

    vector<int> p(n), c(n);
    {
        vector<pair<char, int>> a(n);
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

    vector<int> left(n), right(n);
    stack<pair<int, int>> sl, sr;
    for (int i = 0; i < n; i++)
    {
        int cnt = 1;
        while (!sl.empty() && sl.top().f > lcp[i])
        {
            cnt += sl.top().s;
            sl.pop();
        }
        left[i] = cnt;
        sl.push({lcp[i], cnt});
    }
    for (int i = n - 1; i >= 0; i--)
    {
        int cnt = 1;
        while (!sr.empty() && sr.top().f >= lcp[i])
        {
            cnt += sr.top().s;
            sr.pop();
        }
        right[i] = cnt;
        sr.push({lcp[i], cnt});
    }

    long long ans = (((long long) n) * (n - 1)) / 2;
    for (int i = 0; i < n; i++)
        ans += ((long long) lcp[i]) * left[i] * right[i];

    cout << ans << '\n';

    return 0;
}