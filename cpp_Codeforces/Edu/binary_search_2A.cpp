#include "bits/stdc++.h"
using namespace std;

long long w, h, n;

bool good(long long x)
{
    return (x / w) * (x / h) >= n;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> w >> h >> n;

    long long l = 0, r = 1;
    while (!good(r)) r *= 2;
    while (l + 1 < r)
    {
        long long mid = (l + r) / 2;
        if (good(mid)) r = mid;
        else l = mid;
    }
    cout << r << '\n';
    return 0;
}