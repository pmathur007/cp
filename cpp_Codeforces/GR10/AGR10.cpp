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

        int last = -1;
        bool dif = false;
        for (int i = 0; i < N; i++)
        {
            int a;
            cin >> a;
            if (last == -1) last = a;
            else if (a != last) dif = true;
        }
        cout << (dif ? 1 : N) << '\n';
    }

    return 0;
}