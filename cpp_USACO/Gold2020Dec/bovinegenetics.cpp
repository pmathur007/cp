#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5, MOD = 1e9 + 7;


int N;
int s[MAXN];
long long dp[MAXN][5][5][5];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string str; cin >> str;
    N = str.length();
    for (int i = 0; i < N; i++) s[i + 1] = (str[i] == '?' ? 0 : (str[i] == 'A' ? 1 : (str[i] == 'G' ? 2 : (str[i] == 'C' ? 3 : 4))));

    for (int c = 1; c <= 4; c++)
        for (int a = 1; a <= 4; a++)
            dp[1][c][a][c] = (s[1] == 0 || s[1] == c);
    for (int n = 2; n <= N; n++) {
        for (int c = 1; c <= 4; c++) {
            for (int a = 1; a <= 4; a++) {
                for (int b = 1; b <= 4; b++) {
                    if (s[n] == 0 || s[n] == c) {
                        if (c == b) {
                            for (int d = 1; d <= 4; d++)
                                dp[n][c][a][b] = (dp[n][c][a][b] + dp[n - 1][d][d][a]) % MOD;
                        }
                        if (c != s[n - 1]) {
                            for (int d = 1; d <= 4; d++)
                                dp[n][c][a][b] = (dp[n][c][a][b] + (d != c) * dp[n - 1][d][a][b]) % MOD;
                        }
                    }
                }
            }
        }
    }

    long long ans = 0;
    for (int c = 1; c <= 4; c++)
            for (int b = 1; b <= 4; b++)
                ans = (ans + dp[N][c][c][b]) % MOD;

    cout << ans << '\n';
    
    return 0;
}