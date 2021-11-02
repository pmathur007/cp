#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--)
    {
        int N; cin >> N;
        vector<long long> a(N);
        for (int i = 0; i < N; i++) cin >> a[i];
        sort(a.begin(), a.end());

        long long ans = LONG_LONG_MIN;
        for (int i = N; i >= N - 5; i--)
            ans = max(ans, a[i % N] * a[(i + 1) % N] * a[(i + 2) % N] * a[(i + 3) % N] * a[(i + 4) % N]);
        cout << ans << '\n';

//        int numZeros = 0;
//        vector<long long> pos, neg;
//        for (int i = 0; i < min(N, 5) && a[i] < 0; i++) neg.push_back(a[i]);
//        for (int i = N - 1; i >= max(0, N - 5) && a[i] > 0; i--) pos.push_back(a[i]);
//        for (int i = 0; i < N; i++) numZeros += (a[i] == 0);
//        cout << neg.size() << " " << numZeros << " " << pos.size() << '\n';
//
//        if (N == 5)
//            cout << a[0] * a[1] * a[2] * a[3] * a[4] << '\n';
//        else if (pos.empty())
//        {
//            if (numZeros == 0)
//            {
//                long long ans = 1;
//                for (int i = 0; i < 5; i++) ans *= neg[i];
//                cout << ans << '\n';
//            }
//            else
//                cout << 0 << '\n';
//        }
//        else
//        {
//            long long ans = 0;
//            if (pos.size() >= 5)
//                ans = max(ans, pos[0] * pos[1] * pos[2] * pos[3] * pos[4]);
//            if (pos.size() >= 3)
//            {
//                if (neg.size() >= 2)
//                    ans = max(ans, pos[0] * pos[1] * pos[2] * neg[0] * neg[1]);
//            }
//            if (pos.size() >= 1)
//            {
//                if (neg.size() >= 4)
//                    ans = max(ans, pos[0] * neg[0] * neg[1] * neg[2] * neg[3]);
//            }
//            cout << ans << '\n';
//        }

//        int nn = 0;
//        for (; nn < N && a[nn] < 0; nn++);
//        int pi = nn;
//        for (; pi < N && a[pi] <= 0; pi++);
//
//        if (N == 5 || nn == N)
//        {
//            long long ans = 1;
//            for (int i = 0; i < min(N, 5); i++) ans *= a[i];
//            cout << ans << '\n';
//        }
//        else if (pi == N)
//        {
//            cout << 0 << '\n';
//        }
//        else
//        {
//            long long ans = 0;
//            if (N - pi >= 5)
//            {
//                long long tempAns = 1;
//                for (int i = N - 1; i >= N - 5; i--)
//                    tempAns *= a[i];
//                ans = max(ans, tempAns);
//            }
//            if (N - pi >= 3)
//            {
//                long long tempAns = a[0] * a[1];
//                for (int i = N - 1; i >= N - 3; i--)
//                    tempAns *= a[i];
//                ans = max(ans, tempAns);
//            }
//            if (N - pi >= 1)
//            {
//                long long tempAns = a[N - 1];
//                for (int i = 0; i < 4; i++)
//                    tempAns *= a[i];
//                ans = max(ans, tempAns);
//            }
//            cout << ans << '\n';
//        }
    }

    return 0;
}