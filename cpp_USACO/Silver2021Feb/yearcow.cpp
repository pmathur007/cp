#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, K; cin >> N >> K;

    set<int> s;
    s.insert(0);
    int m = 0;
    for (int i = 0; i < N; i++) {
        int yi; cin >> yi;
        yi = yi / 12 + 1;
        m = max(m, yi);
        s.insert(yi);
    }

    priority_queue<int> pq;
    int prev = -1;
    for (auto &si : s) {
        if (prev != -1)
            pq.push(si - prev - 1);
        prev = si;
    }

    while (K-- > 1 && !pq.empty()) {
        m -= pq.top();
        pq.pop();
    }

    cout << m * 12 << '\n';
    
    return 0;
}