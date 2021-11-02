#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int n, k; cin >> n >> k;
        if (n < k) cout << k - n << '\n';
        else if (n == k) cout << 0 << '\n';
        else cout << ((n - k) % 2 == 1) << '\n';
    }

    return 0;
}