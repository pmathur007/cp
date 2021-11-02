#include "bits/stdc++.h"
using namespace std;

int n, k;
long long a[100005];

bool good(long long v)
{
    long long sum = 0;
    int numSegments = 0;
    for (int i = 0; i < n; i++)
    {
        if (a[i] > v)
            return false;
        if (sum + a[i] > v)
        {
            numSegments++;
            sum = 0;
        }
        sum += a[i];
    }
    if (sum) numSegments++;

    return numSegments <= k;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n >> k;
    for (int i = 0; i < n; i++) cin >> a[i];

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