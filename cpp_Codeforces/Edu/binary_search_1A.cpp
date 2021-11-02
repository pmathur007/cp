#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, K; cin >> N >> K;
    vector<int> a(N);
    for (int i = 0; i < N; i++) cin >> a[i];

    while (K--)
    {
        int x; cin >> x;
        int l = 0, r = N - 1;
        bool found = false;
        while (l <= r)
        {
            int mid = (l + r) / 2;
            if (x == a[mid])
            {
                found = true;
                break;
            }
            else if (x < a[mid])
                r = mid - 1;
            else
                l = mid + 1;
        }
        cout << (found ? "YES" : "NO") << '\n';
    }

    return 0;
}