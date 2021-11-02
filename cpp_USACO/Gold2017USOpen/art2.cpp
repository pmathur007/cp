#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("art2.in", "r", stdin);
    freopen("art2.out", "w", stdout);

    int N; cin >> N;
    vector<int> a(N), last(N + 1);
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        last[a[i]] = i;
    }

    vector<bool> onStack(N + 1, false), closed(N + 1, false);
    stack<int> s;
    int maxs = 0;
    for (int i = 0; i < N; i++) {
        int x = a[i];

        if (closed[x]) {
            cout << -1 << '\n';
            exit(0);
        }
        else if (x == 0) {
            while (!s.empty()) {
                closed[s.top()] = true;
                s.pop();
            }
        }
        else if (onStack[x]) {
            while (s.top() != x) {
                closed[s.top()] = true;
                s.pop();
            }
            if (i == last[x]) {
                closed[x] = true;
                s.pop();
            }
        }
        else {
            s.push(x);
            onStack[x] = true;
            maxs = max(maxs, (int) s.size());
            if (last[x] == i) {
                closed[x] = true;
                s.pop();
            }
        }
    }
    cout << maxs << '\n';

    return 0;
}