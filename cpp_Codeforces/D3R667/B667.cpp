#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        long long a, b, x, y, n; cin >> a >> b >> x >> y >> n;

        if (a - x + b - y <= n)
            cout << x * y << '\n';
        else
        {
            long long deca = a - n >= x ? a - n : x;
            long long decab = b - (n - (a - deca));

            long long decb = b - n >= y ? b - n : y;
            long long decba = a - (n - (b - decb));

            if (deca < x || decab < y)
                cout << decb * decba << '\n';
            else if (decb < y || decba < x)
                cout << deca * decab << '\n';
            else
                cout << min(decb * decba, deca * decab) << '\n';
        }
    }

    return 0;
}