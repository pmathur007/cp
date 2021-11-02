#include "bits/stdc++.h"
using namespace std;

const int MAXN = 305;

int N;
int a[MAXN];
int dp[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) cin >> a[i];

    for (int i = N - 1; i >= 0; i--) {
        for (int j = i + 1; j < N; j++) {
            if (a[i] == a[j]) dp[i][j] = max(dp[i][j], 1 + dp[i + 1][j - 1]);
            for (int k = i + 1; k < j; k++) dp[i][j] = max(dp[i][j], dp[i][k] + dp[k][j]);
        }
    }

    cout << N - dp[0][N - 1] << "\n";
    
    return 0;
}