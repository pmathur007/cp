#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int n, k; cin >> n >> k;
        vector<int> x(n), y(n);
        for (int i = 0; i < n; i++) cin >> x[i];
        for (int i = 0; i < n; i++) cin >> y[i];

        sort(x.begin(), x.end());
        vector<int> numCanSave(n + 1), maxEndSave(n + 1);
        numCanSave[n] = maxEndSave[n] = 0;

        for (int i = n - 1; i >= 0; i--)
        {
            auto up = upper_bound(x.begin(), x.end(), x[i] + k);
            numCanSave[i] = up - x.begin() - i;
            maxEndSave[i] = (i == n - 1 ? numCanSave[i] : max(numCanSave[i], maxEndSave[i + 1]));
        }

//        for (int i = 0; i < n; i++) cout << x[i] << (i == n - 1 ? '\n' : ' ');
//        for (int i = 0; i < n; i++) cout << numCanSave[i] << (i == n - 1 ? '\n' : ' ');
//        for (int i = 0; i < n; i++) cout << maxEndSave[i] << (i == n - 1 ? '\n' : ' ');

        int maxCanSave = 0;
        for (int i = 0; i < n; i++)
        {
            int canSave = numCanSave[i];
            auto up = upper_bound(x.begin(), x.end(), x[i] + k);
            canSave += maxEndSave[up - x.begin()];
            maxCanSave = max(canSave, maxCanSave);
        }

        cout << maxCanSave << '\n';
    }

    return 0;
}