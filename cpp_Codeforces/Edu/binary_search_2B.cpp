#include "bits/stdc++.h"
using namespace std;

int n, k;
int a[10005];

bool good(double x)
{
    int s = 0;
    for (int i = 0; i < n; i++)
        s += floor(a[i] / x);
    return s >= k;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n >> k;
    for (int i = 0; i < n; i++) cin >> a[i];

    double l = 0, r = 1e8;
    for (int i = 0; i < 100; i++)
    {
        double mid = (l + r) / 2;
        if (good(mid))
            l = mid;
        else
            r = mid;
    }
    cout << setprecision(20) << l << '\n';

    return 0;
}