#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N; cin >> N;
    stack<pair<int, int>> stk;
    for (int i = 1; i <= N; i++) {
        int x; cin >> x;
        while (!stk.empty() && stk.top().f >= x) stk.pop();
        cout << (stk.empty() ? 0 : stk.top().s) << (i == N ? "\n" : " ");
        stk.push({x, i});
    }
    
    return 0;
}