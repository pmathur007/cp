#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second
#define f first
#define s second

const int MAXN = 1e5 + 5;

int N, lx, ly, bx, by;
pair<int, int> pts[MAXN];
unordered_map<int, vector<int>> xg, yg;
int d[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("lasers.in", "r", stdin);
    freopen("lasers.out", "w", stdout);

    cin >> N >> lx >> ly >> bx >> by;
    pts[0] = {lx, ly}, pts[1] = {bx, by};

    for (int i = 2; i <= N + 1; i++ ) {
        int xi, yi; cin >> xi >> yi;
        pts[i] = {xi, yi};
    }

    for (int i = 0; i <= N + 1; i++) {
        if (xg.find(pts[i].x) == xg.end()) xg[pts[i].x] = vector<int>();
        if (yg.find(pts[i].y) == yg.end()) yg[pts[i].y] = vector<int>();
        xg[pts[i].x].push_back(i);
        yg[pts[i].y].push_back(i);
    }

    queue<pair<int, int>> q;
    d[0] = 0;
    q.push({0, 0});
    q.push({0, 1});
    bool found = false;
    while (!found && !q.empty()) {
        pair<int, int> v = q.front(); q.pop();
        if (v.f == 1) {
            cout << d[v.f] - 1 << '\n';
            found = true;
        }
        else {
            if (v.s == 0) {
                for (auto &c : xg[pts[v.f].x]) {
                    if (c != 0 && d[c] == 0) {
                        d[c] = d[v.f] + 1;
                        q.push({c, (v.s + 1) % 2});
                    }
                }
            }
            else {
                for (auto &c : yg[pts[v.f].y]) {
                    if (c != 0 && d[c] == 0) {
                        d[c] = d[v.f] + 1;
                        q.push({c, (v.s + 1) % 2});
                    }
                }
            }
        }
    }

    if (!found) cout << -1 << '\n';
    
    return 0;
}