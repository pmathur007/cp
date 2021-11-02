#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("threesum.in", "r", stdin);
    freopen("threesum.out", "w", stdout);

    int N, Q; cin >> N >> Q;
    vector<long> a(N);
    for (int i = 0; i < N; i++) cin >> a[i];

    vector<vector<long>> ans(N, vector<long>(N, 0));
    vector<int> hs(2000001, 0);
    for (int i = 0; i < N; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            if (-(a[i] + a[j]) >= -1000000 && -(a[i] + a[j]) <= 1000000)
                ans[i][j] += hs[-(a[i] + a[j]) + 1000000];
            hs[a[j] + 1000000]++;
        }
        for (int j = i + 1; j < N; j++)
            hs[a[j] + 1000000]--;
    }

    for (int k = 1; k < N; k++)
        for (int i = 0; i + k < N; i++)
            ans[i][i + k] += ans[i + 1][i + k] + ans[i][i + k - 1] - ans[i + 1][i + k - 1];

    while (Q--)
    {
        int ai, bi; cin >> ai >> bi;
        cout << ans[ai - 1][bi - 1] << '\n';
    }

    return 0;
}