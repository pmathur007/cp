#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int N; cin >> N;
        vector<int> a(N), sorted(N);
        int v = 1000000001;

        for (int i = 0; i < N; i++)
        {
            cin >> a[i];
            sorted[i] = a[i];
            v = min(v, a[i]);
        }
        sort(sorted.begin(), sorted.end());

        bool canDo = true;
        for (int i = 0; i < N && canDo; i++)
        {
            if (sorted[i] != a[i] && sorted[i] % v != 0)
            {
                cout << "NO" << '\n';
                canDo = false;
            }
        }
        if (canDo) cout << "YES" << '\n';
    }

    return 0;
}