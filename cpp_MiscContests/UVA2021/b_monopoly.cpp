#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--) {
        int N; cin >> N;
        int cur = 0;
        bool jailed = false;
        for (int i = 0; i < N; i++) {
            int a; cin >> a;
            cur += a;
            if (cur % 44 == 22) jailed = true;
        }
        cout << (jailed ? "JAIL" : "SAFE") << "\n";
    }

    return 0;
}