#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N; cin >> N;
        vector<long long> a(N + 1), d(N);
        int di = -1;
        long long ans = 0;
        for (int i = 0; i <= N; i++)
        {
            if (i < N) cin >> a[i];
            else a[i] = 1e9 + 1;

            if (i > 0 && a[i] < a[i - 1]) d[++di] = a[i - 1];
            if (a[i] > a[i - 1] && di != -1)
            {
                if (a[i] >= d[0])
                {
                    ans += d[0] - a[i - 1];
                    di = -1;
                }
                else
                {
                    ans += a[i] - a[i - 1];
                    while (d[di] < a[i]) di--;
                }
            }
        }
        cout << ans << '\n';
    }

    return 0;
}