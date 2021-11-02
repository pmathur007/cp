#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N;
long long M;
long long f[MAXN], s[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("hayfeast.in", "r", stdin);
    freopen("hayfeast.out", "w", stdout);

    cin >> N >> M;
    for (int i = 0; i < N; i++) cin >> f[i] >> s[i];

    int l = 0;
    long long sum = 0, ans = LONG_LONG_MAX;
    map<long long, int, greater<long long>> cur;
    for (int r = 0; r < N; r++) {
        sum += f[r];
        cur[s[r]] = (cur.find(s[r]) == cur.end() ? 1 : cur[s[r]] + 1);
        if (sum >= M) ans = min(ans, cur.begin()->first);
        while (sum >= M) {
            sum -= f[l];
            cur[s[l]]--;
            if (cur[s[l]] == 0) cur.erase(s[l]);
            l++;
        }
    }

    cout << ans << '\n';
    
    return 0;
}