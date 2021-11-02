#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second

const int MAXN = 1005;
const long long INF = 1000000000000;

int H, G;
pair<int, int> h[MAXN], g[MAXN];
long long dph[MAXN][MAXN], dpg[MAXN][MAXN];

long long dist(pair<int, int> a, pair<int, int> b) {
    return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("checklist.in", "r", stdin);
    freopen("checklist.out", "w", stdout);

    cin >> H >> G;
    for (int n = 1; n <= H; n++) cin >> h[n].x >> h[n].y;
    for (int m = 1; m <= G; m++) cin >> g[m].x >> g[m].y;

    for (int n = 0; n <= H; n++) {
        for (int m = 0; m <= G; m++) {
            if (n == 0) dph[n][m] = dpg[n][m] = INF;
            else if (m == 0) {
                dph[n][m] = n == 1 ? 0 : dph[n - 1][m] + dist(h[n], h[n - 1]);
                dpg[n][m] = INF;
            }
            else {
                dph[n][m] = n == 1 ? INF : min(dpg[n - 1][m] + dist(h[n], g[m]), dph[n - 1][m] + dist(h[n], h[n - 1]));
                dpg[n][m] = min(dph[n][m - 1] + dist(g[m], h[n]), dpg[n][m - 1] + dist(g[m], g[m - 1]));
            }
        }
    }

    cout << dph[H][G] << '\n';

    return 0;
}