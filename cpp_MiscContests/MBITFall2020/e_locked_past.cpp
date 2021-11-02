#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005;

int N, K;
int a[MAXN];
int dc[MAXN][MAXN], ic[MAXN][MAXN];
int ans[MAXN];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> K;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        dc[i][i] = a[i];
        ic[i][i] = K - a[i] + 1;
    }

    for (int sz = 1; sz < N; sz++) {
        for (int i = 0; i < N - sz + 1; i++) {
            dc[i][i + sz] = max(dc[i + 1][i + sz], dc[i][i + sz - 1]);
            ic[i][i + sz] = max(ic[i + 1][i + sz], ic[i][i + sz - 1]);
        }
    }

    fill(ans, ans + N, K);
    ans[0] = min(dc[0][0], ic[0][0]);
    for (int i = 1; i < N; i++)
        for (int j = i; j >= 0; j--)
            ans[i] = min(ans[i], (j == 0 ? 0 : ans[j - 1]) + min(dc[j][i], ic[j][i]));

    for (int i = 0; i < N; i++) cout << ans[i] << '\n';
    cout << '\n';

    cout << ans[N - 1] << '\n';

    return 0;
}