#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N;
        cin >> N;
        vector<int> a(N);
        for (int i = 0; i < N; i++) cin >> a[i];

        if (a[0] + a[1] <= a[N - 1]) cout << 1 << ' ' << 2 << ' ' << N << '\n';
        else cout << -1 << '\n';
    }

    return 0;
}