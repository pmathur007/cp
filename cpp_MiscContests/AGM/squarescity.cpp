#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int Q; cin >> Q;
    vector<long long> pow2(62);
    pow2[0] = 1;
    for (int i = 1; i <= 61; i++) pow2[i] = 2 * pow2[i - 1];


    while (Q--) {
        long long N, X, Y; cin >> N >> X >> Y;
        int K = log2(N);

        int p = K - 1;
        for (; p >= 0; p--) {
            if (X <= pow2[p] && Y <= pow2[p]) break;

            if (X > pow2[p]) X -= pow2[p];
            if (Y > pow2[p]) Y -= pow2[p];
        }

        cout << pow2[p] << '\n';
    }
    
    return 0;
}