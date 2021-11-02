#include "bits/stdc++.h"
using namespace std;

const int MAXN = 5e5 + 5;

int N;
long long a[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    long long sum = 0;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        sum += a[i];
    }

    long long avg = sum / N;
    long long m = a[0] % avg;
    long long cur = 0, ans = 0;
    for (int i = 0; i < N; i++) {
        cur = (cur + a[i]) % avg;
        if (cur == m) ans++;
    }
    cout << N - ans << "\n";

    return 0;
}