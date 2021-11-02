#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 +5, MAXX = 1e6 + 5;

int N;
int x[MAXN], totd[MAXX];
bool d[MAXX];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> x[i];
        for (int di = 1; di * di <= x[i]; di++) {
            if (x[i] % di == 0) {
                totd[di]++;
                totd[x[i] / di]++;
            }
        }
    }

    vector<pair<long long, int>> xc(N);
    for (int i = 0; i < N; i++) {
        xc[i].first = 0, xc[i].second = x[i];
        for (int di = 1; di * di <= x[i]; di++) {
            if (x[i] % di == 0) {
                xc[i].first += totd[di];
                xc[i].first += totd[x[i] / di];
            }
        }
    }
    sort(xc.begin(), xc.end(), greater<pair<long long, int>>());

    long long ans = 0;
    for (int i = 0; i < N; i++) {
        vector<int> divisors;
        for (int di = 1; di * di <= x[i]; di++) {
            if (x[i] % di == 0) {
                divisors.push_back(di);
                divisors.push_back(x[i] / di);
            }
        }

        int maxd = 0;
        for (auto &ds : divisors)
            if (d[ds])
                maxd = max(maxd, ds);
        ans += maxd;

        for (auto &ds : divisors) d[ds] = true;
    }

    cout << ans << '\n';
}