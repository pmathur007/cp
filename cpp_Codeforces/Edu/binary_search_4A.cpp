#include "bits/stdc++.h"
using namespace std;

int n, d;
double a[100005];

bool good(double x)
{
    vector<double> an(n), p(n), m(n);
    for (int i = 0; i < n; i++)
    {
        an[i] = a[i] - x;
        p[i] = an[i] + (i == 0 ? 0 : p[i - 1]);
        m[i] = min(p[i], (i == 0 ? 300 : m[i - 1]));
    }

    for (int i = d - 1; i < n; i++)
        if (p[i] >= 0 || p[i] - (i == d - 1 ? 0 : m[i - d]) >= 0)
            return true;
    return false;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n >> d;
    for (int i = 0; i < n; i++) cin >> a[i];

    good(9.6);

    double l = -1, r = 200;
    for (int i = 0; i < 200; i++)
    {
        double mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    vector<double> an(n), p(n), m(n);
    for (int i = 0; i < n; i++)
    {
        an[i] = a[i] - l;
        p[i] = an[i] + (i == 0 ? 0 : p[i - 1]);
        m[i] = (i == 0 || p[i] < p[m[i - 1]]) ? i : m[i - 1];
    }
    for (int i = d - 1; i < n; i++)
    {
        if (p[i] >= 0)
        {
            cout << 1 << " " << i + 1 << '\n';
            break;
        }
        else if (p[i] - (i == d - 1 ? 0 : p[m[i - d]]) >= 0)
        {
            cout << (i == d - 1 ? 1 : m[i - d] + 2) << " " << i + 1 << '\n';
            break;
        }
    }

    return 0;
}