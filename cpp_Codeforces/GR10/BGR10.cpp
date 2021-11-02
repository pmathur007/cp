#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N; cin >> N;
        long long K; cin >> K;
        vector<long long> a(N);
        long long maxa = -10e9 - 1, mina = 10e9 + 1;

        for (int i = 0; i < N; i++)
        {
            cin >> a[i];
            maxa = max(maxa, a[i]), mina = min(mina, a[i]);
        }

        for (int i = 0; i < N; i++)
            cout << (K % 2 == 1 ? maxa - a[i] : a[i] - mina) << (i == N - 1 ? '\n' : ' ');
    }

    return 0;
}