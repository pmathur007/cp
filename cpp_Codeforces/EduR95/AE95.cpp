#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--)
    {
        double x, y, k; cin >> x >> y >> k;
        cout << (long long) (floor(k + ((y * k) + k - 1) / (x - 1))) << '\n';
    }

    return 0;
}