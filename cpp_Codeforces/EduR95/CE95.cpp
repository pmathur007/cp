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

        if (N <= 2)
            cout << a[0] << '\n';
        else
        {
            vector<int> dpu(N);
            vector<int> dpf(N);

            dpu[N - 1] = dpu[N - 2] = 0;
            dpf[N - 1] = a[N - 1];
            dpf[N - 2] = a[N - 2];

            for (int i = N - 3; i >= 0; i--)
            {
                dpu[i] = min(dpf[i + 1], dpf[i + 2]);
                dpf[i] = min(a[i] + dpu[i + 1], a[i] + a[i + 1] + dpu[i + 2]);
            }

            cout << dpf[0] << '\n';
        }
    }

    return 0;
}