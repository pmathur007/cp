#include "bits/stdc++.h"
using namespace std;

int n;
double x[100005], v[100005];

bool good(double t)
{
    double maxl = -1e20, minr = 1e20;
    for (int i = 0; i < n; i++)
    {
        maxl = max(maxl, x[i] - t * v[i]);
        minr = min(minr, x[i] + t * v[i]);
    }
    return maxl <= minr;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n;
    for (int i = 0; i < n; i++) cin >> x[i] >> v[i];

    double l = -1, r = 1e10;
    for (int i = 0; i < 100; i++)
    {
        double mid = (l + r) / 2;
        if (good(mid)) r = mid;
        else l = mid;
    }
    cout << setprecision(20) << r << '\n';

    return 0;
}