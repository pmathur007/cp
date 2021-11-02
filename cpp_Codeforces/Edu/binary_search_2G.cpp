#include "bits/stdc++.h"
using namespace std;

int k, n;
long long a[55];

bool good(long long v)
{
    long long sum = 0;
    for (int i = 0; i < n; i++) sum += min(v, a[i]);
    return sum >= v * k;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> k >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    sort(a, a + n);

    long long l = 0, r = 1;
    while (good(r)) r *= 2;

    while (l + 1 < r)
    {
        long long mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    cout << l << '\n';

    return 0;
}