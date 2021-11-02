#include "bits/stdc++.h"
using namespace std;

const int MAXN = 105;

int N, MAXA = 0;
double MAXB = 0;
int a[MAXN], b[MAXN];
double dp[MAXN][MAXN * MAXN];
double ans[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int n = 1; n <= N; n++) {
        cin >> a[n] >> b[n];
        MAXA += a[n];
        MAXB += b[n];
    }

    for (int k = 0; k <= N; k++)
        for (int A = 1; A <= MAXA; A++)
            dp[k][A] = -1;

    for (int n = 1; n <= N; n++) {
        for (int A = MAXA; A >= 0; A--) {
            for (int k = 1; k <= N; k++) {
                if (a[n] <= A && dp[k - 1][A - a[n]] != -1)
                    dp[k][A] = max(dp[k][A], dp[k - 1][A - a[n]] + b[n]);
            }
        }
    }

    for (int k = 1; k <= N; k++)
        for (int A = 0; A <= MAXA; A++)
            if (dp[k][A] != -1)
                ans[k] = max(ans[k], min(((double) A), dp[k][A]/2 + MAXB/2));

    for (int k = 1; k <= N; k++) cout << ans[k] << (k == N ? "\n" : " ");

    return 0;
}