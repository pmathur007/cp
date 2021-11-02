#include "bits/stdc++.h"
using namespace std;

const int MAXN = 10005, MAXP = 1234;

int N, M, P = 0;
bool isPrime[MAXN];
int p[MAXP];
long dp[MAXP][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("exercise.in", "r", stdin);
    freopen("exercise.out", "w", stdout);

    cin >> N >> M;

    for (int i = 2; i <= N; i++) {
        if (!isPrime[i]) {
            p[++P] = i;
            for (int j = i; j <= N; j += i) isPrime[j] = true;
        }
    }

    for (int n = 0; n <= N; n++) dp[0][n] = 1;
    for (int k = 1; k <= P; k++) {
        for (int n = 0; n <= N; n++) {
            dp[k][n] = dp[k - 1][n];
            for (int pk = p[k]; pk <= n; pk *= p[k]) {
                dp[k][n] = (dp[k][n] + (pk * dp[k - 1][n - pk] + M) % M) % M;
            }
        }
    }

    cout << dp[P][N] << '\n';
    
    return 0;
}