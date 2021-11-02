#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int x1, y1, z1; cin >> x1 >> y1 >> z1;
        int x2, y2, z2; cin >> x2 >> y2 >> z2;

        int ans = 0;
        ans += 2 * min(z1, y2);
        z1 = max(0, z1 - y2);
        z2 = max(0, z2 - x1 - z1);
        ans += -2 * min(z2, y1);
        cout << ans << '\n';
    }

    return 0;
}