#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    const long MOD = 1e9 + 7;
    int N, M; cin >> N >> M;

    long f = 1;
    for (int i = 1; i <= N; i++) f = (f * i) % MOD;
    double fact = f;

    double ans = 0;
    for (int i = 0; i < M; i++)
    {
        double c; cin >> c;
        ans += (c * (c - N - 1)) / ((N - c + 1) * (c + 1));
    }

    ans *= fact;
    ans *= (N + 1);

    cout << -ans << '\n';

    return 0;
}