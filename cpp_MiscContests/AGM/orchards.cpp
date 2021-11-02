#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005, MAXK = 505;

struct rect {
    int x1, y1, x2, y2;
};

int N, M, K;
rect r[MAXK];
int grid[MAXN][MAXN];
vector<int> g[MAXK];
int l[MAXK];

bool isConnected(int i, int j) {
    if (abs(r[i].y2 - r[j].y1) == 1 && r[i].x2 - r[j].x1 >= -1 && r[i].x1 - r[j].x2 <= 1) return true;
    if (abs(r[i].y1 - r[j].y2) == 1 && r[i].x2 - r[j].x1 >= -1 && r[i].x1 - r[j].x2 <= 1) return true;
    if (abs(r[i].x2 - r[j].x1) == 1 && r[i].y2 - r[j].y1 >= -1 && r[i].y1 - r[j].y2 <= 1) return true;
    if (abs(r[i].x1 - r[j].x2) == 1 && r[i].y2 - r[j].y1 >= -1 && r[i].y1 - r[j].y2 <= 1) return true;
    return false;
}

bool label(int v, int i) {
    l[v] = i;
    for (auto &c : g[v]) {
        if (l[c] == i) return false;
        if (l[c] == 0)
            if (!label(c, l[v] == 1 ? 2 : 1))
                return false;
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M >> K;
    for (int k = 0; k < K; k++) {
        int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
        r[k] = {x1 - 1, y1 - 1, x2 - 1, y2 - 1};
        for (int i = 0; i < k; i++) {
            if (isConnected(k, i)) {
                g[k].push_back(i);
                g[i].push_back(k);
            }
        }
    }

    for (int n = 0; n < N; n++)
        for (int m = 0; m < M; m++)
            cin >> grid[n][m];

    if (!label(0, 1)) cout << "-1\n";
    else {
        vector<int> g1f(101), g2f(101);
        int g1sum = 0, g1above = 0, g2sum = 0, g2above = 0;
        for (int k = 0; k < K; k++) {
            for (int x = r[k].x1; x <= r[k].x2; x++) {
                for (int y = r[k].y1; y <= r[k].y2; x++) {
                    if (l[k] == 1) {
                        g1f[grid[x][y]]++;
                        g1sum += grid[x][y] - 1;
                        g1above++;
                    }
                    else {
                        g2f[grid[x][y]]++;
                        g2sum += grid[x][y] - 1;
                        g2above++;
                    }
                }
            }
        }

        int g1min = g1sum, g1cur = g1sum, g2min = g2sum, g2cur = g2sum;
        int g1below = 0, g2below = 0;
        for (int i = 2; i <= 100; i++) {
            g1above -= g1f[i];
            g1below += g1f[i - 1];
            g1min = min(g1min, g1cur - (g1above - g1f[i]) + (g1below + g1f[i - 1]));
        }
    }

    return 0;
}