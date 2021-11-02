#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int n, x, y; cin >> n >> x >> y;
        int minDif = 51;
        for (int i = 1; i <= 50; i++)
            if ((y - x) % i == 0 && ((y - x) / i + 1) <= n)
                minDif = min(minDif, i);

        vector<int> a(n);
        int idx = 0;
        for (int i = x; i <= y; i += minDif)
            a[idx++] = i;
        if (idx < n)
        {
            for (int i = x - minDif; idx < n && i > 0; i -= minDif)
                a[idx++] = i;
        }
        if (idx < n)
        {
            for (int i = y + minDif; idx < n; i += minDif)
                a[idx++] = i;
        }
        for (int i = 0; i < n; i++) cout << a[i] << (i == n - 1 ? '\n' : ' ');
    }

    return 0;
}