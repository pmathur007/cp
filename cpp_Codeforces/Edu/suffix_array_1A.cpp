#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string str;
    cin >> str;
    str += "$";

    int n = str.length();
    vector<int> p(n), c(n);

    // k = 0
    vector<pair<char, int>> a(n);
    for (int i = 0; i < n; i++) a[i] = {str[i], i};
    sort(a.begin(), a.end());

    for (int i = 0; i < n; i++) p[i] = a[i].s;

    c[p[0]] = 0;
    for (int i  = 1; i < n; i++)
    {
        if (str[p[i]] != str[p[i - 1]])
            c[p[i]] = 1 + c[p[i - 1]];
        else
            c[p[i]] = c[p[i - 1]];
    }

    // k >= 1
    int k = 0;
    while (n > (1 << k))
    {
        vector<pair<pair<int, int>, int>> a(n);
        for (int i = 0; i < n; i++) a[i] = {{c[i], c[(i + (1 << k)) % n]}, i};
        sort(a.begin(), a.end());

        for (int i = 0; i < n; i++) p[i] = a[i].s;

        c[p[0]] = 0;
        for (int i = 1; i < n; i++)
        {
            if (a[i].f.f != a[i - 1].f.f || a[i].f.s != a[i - 1].f.s)
                c[p[i]] = 1 + c[p[i - 1]];
            else
                c[p[i]] = c[p[i - 1]];
        }
        k++;
    }

    for (int i = 0; i < n; i++) cout << p[i] << (i == n - 1 ? '\n' : ' ');

    return 0;
}