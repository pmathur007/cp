#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, Q; cin >> N >> Q;

    string s; cin >> s;
    vector<int> a(N + 1);
    for (int i = 1; i <= N; i++) a[i] = s[i - 1] - 65;

    vector<int> inc(N + 2, 0), dec(N + 2, 0);
    stack<int> incq, decq;
    for (int i = 1; i <= N; i++) {
        inc[i] = inc[i - 1];
        while (!incq.empty() && incq.top() > a[i]) incq.pop();
        if (incq.empty() || incq.top() != a[i]) {
            inc[i]++;
            incq.push(a[i]);
        }

        int j = N - i + 1;
        dec[j] = dec[j + 1];
        while (!decq.empty() && decq.top() > a[j]) decq.pop();
        if (decq.empty() || decq.top() != a[j]) {
            dec[j]++;
            decq.push(a[j]);
        }
    }

    while (Q--) {
        int a, b; cin >> a >> b;
        cout << inc[a - 1] + dec[b + 1] << '\n';
    }

    return 0;
}