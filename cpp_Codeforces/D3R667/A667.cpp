#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int a, b; cin >> a >> b;
        int dif = abs(a - b);
        if (dif % 10 == 0)
            cout << dif / 10 << '\n';
        else
            cout << (dif / 10 + 1) << '\n';
    }

    return 0;
}