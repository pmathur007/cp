#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, K; cin >> N >> K;
    string s; cin >> s;

    map<string, int> f;
    string ans = s.substr(0, K);
    for (int i = 0; i <= N - K; i++) {
        string cur = s.substr(i, K);
        f[cur]++;
        if (f[cur] == f[ans] && cur < ans) ans = cur;
        if (f[cur] > f[ans]) ans = cur;
    }
    cout << ans << "\n";
    
    return 0;
}