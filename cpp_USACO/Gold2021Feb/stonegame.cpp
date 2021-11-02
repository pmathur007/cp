#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N;
int a[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;

    int m = 0;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        m = max(m, (a[i] / 2) + 1);
    }

    if (N == 2) {
        if (a[0] > a[1]) swap(a[0], a[1]);
        cout << min(a[1] - m + 1, a[1] - a[0]) << "\n";
    }
    else {
        long long ans = 0;
        for (int i = 0; i < N; i++)
            ans += max(0, a[i] - m + 1);
        cout << ans << "\n";
    }

    return 0;
}