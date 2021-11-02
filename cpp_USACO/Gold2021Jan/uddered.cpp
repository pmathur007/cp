#include "bits/stdc++.h"
using namespace std;

const int MAXN = 20, MAXM = 1e5 + 5;

int N, M;
unordered_map<char, int> ltoi;
int a[MAXM], dp[(1 << MAXN) + 5];
int c[MAXN][MAXN];

int cost(int k, int mask) {
    int cst = c[k][k];
    for (int i = 0; i < N; i++)
        if ((mask & (1 << i)) != 0)
            cst += c[k][i];
    return cst;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string s; cin >> s;

    M = s.length();
    int idx = 0;
    for (int i = 0; i < M; i++) {
        if (ltoi.find(s[i]) == ltoi.end())
            ltoi[s[i]] = idx++;
        a[i] = ltoi[s[i]];
    }

    N = ltoi.size();
    for (int i = 0; i < M - 1; i++) c[a[i]][a[i + 1]]++;

    for (int mask = 0; mask < (1 << N); mask++) dp[mask] = MAXM;
    dp[0] = 0;

    for (int mask = 0; mask < (1 << N); mask++)
        for (int k = 0; k < N; k++)
            if ((mask & (1 << k)) == 0)
                dp[mask | (1 << k)] = min(dp[mask | (1 << k)], dp[mask] + cost(k, mask));

    cout << dp[(1 << N) - 1] + 1 << "\n";

    return 0;
}