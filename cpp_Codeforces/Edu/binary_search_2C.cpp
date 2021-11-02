#include "bits/stdc++.h"
using namespace std;

int n, x, y;

bool good(long long v)
{
    return v / x + v /y >= n;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n >> x >> y;
    n--;
    if (n == 0)
    {
        cout << min(x, y) << '\n';
        return 0;
    }

    long long l = 0, r = 1;
    while (!good(r)) r *= 2;

    while (l + 1 < r)
    {
        long long mid = (l + r) / 2;
        if (good(mid)) r = mid;
        else l = mid;
    }

    cout << r + min(x, y) << '\n';

    return 0;
}