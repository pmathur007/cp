#include "bits/stdc++.h"
using namespace std;

double C;
bool good(double x)
{
    return x * x + sqrt(x) <= C;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> C;

    double l = 0, r = 1e10;
    for (int i = 0; i < 100; i++)
    {
        double mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    cout << setprecision(20) << l << '\n';

    return 0;
}