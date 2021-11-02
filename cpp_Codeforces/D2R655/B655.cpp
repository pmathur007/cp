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

        bool found = false;
        for (int i = 2; i < ((int) sqrt(N)) + 1; i++)
        {
            if (N % i == 0)
            {
                cout << N / i << ' ' << N - (N / i) << '\n';
                found = true;
                break;
            }
        }

        if (!found)
            cout << 1 << ' ' << N - 1 << '\n';
    }

    return 0;
}