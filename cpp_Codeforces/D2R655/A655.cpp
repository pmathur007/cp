#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N;
        cin >> N;

        for (int i = 0; i < N; i++)
            cout << 1 << (i == N - 1 ? '\n' : ' ');
    }

    return 0;
}