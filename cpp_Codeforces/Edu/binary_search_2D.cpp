#include "bits/stdc++.h"
using namespace std;

long long m, n;
int t[1005], y[1005], z[1005];

bool good(long long v)
{
    long long num = 0;
    for (int i = 0; i < n; i++)
    {
        long long temp = v;
        num += (v / (z[i] * t[i] + y[i])) * z[i];
        temp -= (v / (z[i] * t[i] + y[i])) * (z[i] * t[i] + y[i]);
        if (z[i] * t[i] <= temp)
            num += z[i];
        else
            num += (temp / t[i]);
    }
    return num >= m;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> m >> n;
    for (int i = 0; i < n; i++) cin >> t[i] >> z[i] >> y[i];

    if (m == 0)
    {
        cout << 0 << '\n';
        for (int i = 0; i < n; i++) cout << 0 << (i == n - 1 ? '\n' : ' ');
    }
    else
    {
        long long l = 0, r = 1;
        while (!good(r)) r *= 2;

        while (l + 1 < r)
        {
            long long mid = (l + r) / 2;
            if (good(mid)) r = mid;
            else l = mid;
        }

        cout << r << '\n';

        for (int i = 0; i < n; i++)
        {
            long long num = 0;
            long long temp = r;
            num += (r / (z[i] * t[i] + y[i])) * z[i];
            temp -= (r / (z[i] * t[i] + y[i])) * (z[i] * t[i] + y[i]);
            if (z[i] * t[i] <= temp)
                num += z[i];
            else
                num += (temp / t[i]);
            cout << min(m, num) << (i == n - 1 ? '\n' : ' ');
            m -= num;
        }
    }

    return 0;
}