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
        int l = -1, r = N;
        while (l + 1 < r)
        {
            int mid = (l + r) / 2;
            if (a[mid] < x)
                l = mid;
            else
                r = mid;
        }
        cout << r + 1 << '\n';
    }

    return 0;
}