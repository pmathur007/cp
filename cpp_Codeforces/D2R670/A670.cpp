#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--)
    {
        int N; cin >> N;
        vector<int> a(N);
        for (int i = 0; i < N; i++) cin >> a[i];
        sort(a.begin(), a.end());

        int i = 0, cur = 0, lasta = -1, lastb = -1;
        while (i + 1 < N && a[i] == cur && a[i + 1] == cur)
        {
            lasta = lastb = cur;
            while (i < N && a[i] == cur) i++;
            cur++;
        }
        while (i < N && a[i] == lasta + 1)
        {
            lasta++;
            while (i < N && a[i] == lasta) i++;
        }
        cout << lasta + lastb + 2 << '\n';
    }

    return 0;
}