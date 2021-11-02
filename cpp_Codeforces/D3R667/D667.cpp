#include "bits/stdc++.h"
using namespace std;

long long p10[19];

long long sumOfDigits(long long n)
{
    long long sum = 0;
    while (n > 0)
    {
        sum += n % 10;
        n /= 10;
    }
    return sum;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    p10[0] = 1;
    for (int i = 1; i < 19; i++)
        p10[i] = 10 * p10[i - 1];

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        long long n; cin >> n;
        int s; cin >> s;

        if (sumOfDigits(n) <= s)
            cout << 0 << '\n';
        else
        {
            long long minInc = LONG_LONG_MAX;
            for (int i = 1; i <= 18; i++)
            {
                if (n % p10[i] != 0)
                {
                    long long inc = p10[i] - (n % p10[i]);
                    long long newn = n + inc;
                    if (sumOfDigits(newn) <= s)
                        minInc = min(minInc, inc);
                }
            }
            cout << minInc << '\n';
        }
    }

    return 0;
}