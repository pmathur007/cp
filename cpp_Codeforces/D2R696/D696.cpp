#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--) {
        int N; cin >> N;
        vector<long long> a(N);
        for (int i = 0; i < N; i++) cin >> a[i];

        if (N == 1) cout << "NO\n";
        else if (N == 2) cout << (a[0] == a[1] ? "YES\n" : "NO\n");
        else if (N == 3) cout << (a[2] == abs(a[1] - a[0]) || a[0] == abs(a[1] - a[2]) || a[1] == abs(a[0] - a[2]) ? "YES\n" : "NO\n");
        else {
            vector<long long> p(N, -1), s(N, -1);
            p[0] = a[0];
            for (int i = 1; i < N; i++)
                if (p[i - 1] != -1)
                    p[i] = max(-1LL, a[i] - p[i - 1]);

            if (p[N - 1] == 0) {
                cout << "YES\n";
                continue;
            }

            s[N - 1] = a[N - 1];
            for (int i = N - 2; i >= 0; i--)
                if (s[i + 1] != -1)
                    s[i] = max(-1LL, a[i] - s[i + 1]);

            if ((s[2] != -1 && a[1] - (a[0] - s[2]) == 0) || (p[N - 3] != -1 && a[N - 2] - (a[N - 1] - p[N - 3]) == 0)) {
                cout << "YES\n";
                continue;
            }

            bool works = false;
            for (int i = 3; i < N; i++) {
                if (s[i] == -1 || p[i - 3] == -1) continue;
                else {
                    long long cur = a[i - 1] - p[i - 3];
                    if (cur < 0) continue;
                    cur = a[i - 2] - cur;
                    if (cur < 0) continue;
                    cur = s[i] - cur;
                    if (cur == 0) {
                        cout << "YES\n";
                        works = true;
                        break;
                    }
                }
            }
            if (!works) cout << "NO\n";
        }
    }
    
    return 0;
}