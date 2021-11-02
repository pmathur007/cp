#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second

const int MAXN = 45;

int N;
pair<int, int> p[MAXN];

struct pt {
    int x, y;
    pt() {}
    pt(int _x, int _y) : x(_x), y(_y) {}
    pt operator-(const pt& p) const { return pt(x - p.x, y - p.y); }
    int cross(const pt& p) const { return x * p.y - y * p.x; }
    int cross(const pt& a, const pt& b) const { return (a - *this).cross(b - *this); }
};

int sgn(const int& x) {
    return x >= 0 ? x ? 1 : 0 : -1;
}

bool intersect(int a, int b, int c, int d) {
    if (a > b) swap(a, b);
    if (c > d) swap(c, d);
    return max(a, c) <= min(b, d);
}

bool intersect(const pt& a, const pt& b, const pt& c, const pt& d) {
    if (c.cross(a, d) == 0 && c.cross(b, d) == 0)
        return intersect(a.x, b.x, c.x, d.x) && intersect(a.y, b.y, c.y, d.y);
    return sgn(a.cross(b, c)) != sgn(a.cross(b, d)) &&
           sgn(c.cross(d, a)) != sgn(c.cross(d, b));
}

bool check(vector<int> &perm) {
    vector<pair<pt, pt>> seg;
    seg.push_back({pt(p[perm[0]].x, p[perm[0]].y), pt(p[perm[1]].x, p[perm[1]].y)});
    seg.push_back({pt(p[perm[1]].x, p[perm[1]].y), pt(p[perm[2]].x, p[perm[2]].y)});
    seg.push_back({pt(p[perm[2]].x, p[perm[2]].y), pt(p[perm[0]].x, p[perm[0]].y)});
    for (int i = 3; i < N; i++) {
        int numSeg = 0;
        for (int j = 0; j < i; j++) {
            bool inter = false;
            for (auto &s : seg) {
                if (intersect(pt(p[perm[i]].x, p[perm[i]].y), pt(p[perm[j]].x, p[perm[j]].y), s.first, s.second)) {
                    if (!(p[perm[i]].x == s.first.x && p[perm[i]].y == s.first.y)
                        && !(p[perm[j]].x == s.first.x && p[perm[j]].y == s.first.y)
                        && !(p[perm[i]].x == s.second.x && p[perm[i]].y == s.second.y)
                        && !(p[perm[j]].x == s.second.x && p[perm[j]].y == s.second.y)) {
                        inter = true;
                        break;
                        }
                }
            }
            if (!inter) {
                numSeg++;
                seg.push_back({pt(p[perm[i]].x, p[perm[i]].y), pt(p[perm[j]].x, p[perm[j]].y)});
            }
        }
        if (numSeg != 3) return false;
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) cin >> p[i].x >> p[i].y;

    long long ans = 1;
    long long M = 1e9 + 7;
    long long f;
    for (int i = N; i > 1; i++) ans = (ans * i) % M;


    for (int a = 0; a < N; a++) {
        for (int b = a + 1; b < N; b++) {
            for (int c = b + 1; c < N; c++) {
                for (int d = c + 1; d < N; d++) {
                    int numInt = 0;
                    numInt += intersect(pt(p[a].x, p[a].y), pt(p[b].x, p[b].y), pt(p[c].x, p[c].y), pt(p[d].x, p[d].y));
                    numInt += intersect(pt(p[a].x, p[a].y), pt(p[c].x, p[c].y), pt(p[b].x, p[b].y), pt(p[d].x, p[d].y));
                    numInt += intersect(pt(p[a].x, p[a].y), pt(p[d].x, p[d].y), pt(p[b].x, p[b].y), pt(p[c].x, p[c].y));
                    
                }
            }
        }
    }

    long long ans = 0;

    int idx = 0;
    vector<int> perm(N);
    for (int i = 0; i < N; i++) perm[i] = i;
    ans += check(perm);

    vector<int> c(N, 0);
    while (idx < N) {
        if (c[idx] < idx) {
            if(idx % 2 == 0) swap(perm[0], perm[idx]);
            else swap(perm[c[idx]], perm[idx]);

            ans += check(perm);
            c[idx]++;
            idx = 0;
        }   
        else {
            c[idx] = 0;
            idx++;
        }
    }

    cout << ans << "\n";

    return 0;
}