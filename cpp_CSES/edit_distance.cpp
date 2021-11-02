#include "bits/stdc++.h"
using namespace std;

const int MAXNM = 5005;

int N, M;
string s1, s2;
int dp[MAXNM][MAXNM];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> s1 >> s2;
    N = s1.length(), M = s2.length();

    for (int n = 0; n <= N; n++) dp[n][0] = n;
    for (int m = 0; m <= M; m++) dp[0][m] = m;
    for (int n = 1; n <= N; n++)
        for (int m = 1; m <= M; m++)
            dp[n][m] = min((s1[n - 1] != s2[m - 1]) + dp[n - 1][m - 1], min(1 + dp[n - 1][m], 1 + dp[n][m - 1]));

    cout << dp[N][M] << '\n';
    
    return 0;
}