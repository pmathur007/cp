#include "bits/stdc++.h"
using namespace std;

const int MAXN = 2e5 + 5;

int N;
int last[MAXN], recent[MAXN], fwt[MAXN];

void update(int i, int v) {
    for (; i <= N; i += (i & -i)) fwt[i] += v;
}

long long query(int i) {
    long long sum = 0;
    for (; i > 0; i -= (i & -i)) sum += fwt[i];
    return sum;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    long long ans = 0;
    for (int i = 1; i <= N; i++) {
        int b; cin >> b;
        if (recent[b] != 0) update(recent[b], -1);
        last[b] = recent[b];
        recent[b] = i;
        ans += query(recent[b]) - query(last[b] - 1);
        update(recent[b], 1);
    }

    cout << ans << "\n";

    return 0;
}