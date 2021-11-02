#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--) {
        long long M, N; cin >> M >> N;
        long long ans = N;
        for (int k = N - M; k > 0; k -= M) {
            ans += 2 * k;
        }
        cout << ans << "\n";
    }

    return 0;
}