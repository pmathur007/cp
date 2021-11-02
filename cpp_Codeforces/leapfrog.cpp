#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    long long N, Q; cin >> N >> Q;
    for (int q = 0; q < Q; q++)
    {
        long long x; cin >> x;
        long long moves = x - 1;

        long long skipidx = N - 1 - moves + (N - x);
        while (2 * skipidx <= N - 1) skipidx = 2 * skipidx;
        cout << skipidx << '\n';
//        cout << (N - skipidx) + (N - 2 - skipidx) << '\n';
    }

    return 0;
}