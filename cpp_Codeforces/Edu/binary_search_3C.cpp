#include "bits/stdc++.h"
using namespace std;

int n, k;
int a[10005];

bool good(int d)
{
    int numCows = 1, last = a[0];
    for (int i = 1; i < n; i++)
    {
        if (a[i] - last >= d)
        {
            numCows++;
            last = a[i];
        }
    }
    return numCows >= k;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> n >> k;
    for (int i = 0; i < n; i++) cin >> a[i];

    int l = 0, r = 1e9 + 5;
    while (l + 1 < r)
    {
        int mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    cout << l << '\n';

    return 0;
}