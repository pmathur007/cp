#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second

const int MAXN = 205;

int N;
pair<int, int> xp[MAXN], yp[MAXN];

bool compX(const pair<int, int> &a, const pair<int, int> &b) {
    return a.x < b.x;
}

bool compY(const pair<int, int> &a, const pair<int, int> &b) {
    return a.y < b.y;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int n = 0; n < N; n++) {
        int xn, yn; cin >> xn >> yn;
        xp[n].x = yp[n].x = xn;
        xp[n].y = yp[n].y = yn;
    }
    sort(xp, xp + N);
    sort(yp, yp + N, compY);

    unordered_map<int, int> xh, yh;
    for (int n = 0; n < N; n++)
        xh[xp[n].x] = yh[yp[n].y] = n;

    int overlap = 0;
    int lrct = 0, tbct = 0;

    for (int l = 0; l < N; l++) {
        for (int r = l + 1; r < N; r++) {
            if (abs(xp[r].x - xp[l].x) >= abs(xp[r].y - xp[l].y)) {
                int w = abs(xp[r].x - xp[l].x);
                int miny = min(xp[l].y, xp[r].y), maxy = max(xp[l].y, xp[r].y);
                int t = yh[maxy], curt = maxy;
                int b = 0;
                for (; yp[b].y + w < yp[t].y; b++);

                while (b < xh[miny]) {
                    if (t != N && yp[t].y - yp[b].y == w) {
                        cout << l << " " << r << " " << t << " " << b << '\n';
                        overlap++;
                    }
                    int td = t == N ? 2000000001 : yp[t + 1].y - curt;
                    int bd = yp[b + 1].y - curt + w;

                    if (td == bd) {
                        t++; b++;
                        curt += td;
                    }
                    else if (td < bd) {
                        t++;
                        curt += td;
                    }
                    else {
                        b++;
                        curt += bd;
                    }
                    lrct++;
                }
            }
        }
    }

    cout << overlap << '\n';
    
    return 0;
}