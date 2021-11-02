#include "bits/stdc++.h"
using namespace std;

#define x first
#define y second

const int MAXN = 2505;

int N;
pair<int, int> xc[MAXN];
bool haspt[MAXN][MAXN];
int pre[MAXN][MAXN];

bool sortByY(const pair<int, int> &a, const pair<int, int> &b) {
    return (a.y < b.y);
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int n = 1; n <= N; n++) {
        int xn, yn; cin >> xn >> yn;
        xc[n].x = xn, xc[n].y = yn;
    }

    sort(xc + 1, xc + N + 1);
    for (int i = 1; i <= N; i++)
        xc[i].x = i;
    sort(xc + 1, xc + N + 1, sortByY);
    for (int i = 1; i <= N; i++)
        xc[i].y = i;
    sort(xc + 1, xc + N + 1);

    for (int i = 1; i <= N; i++) haspt[xc[i].x][xc[i].y] = true;
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + haspt[i][j];

    long long ans = 0;
    for (int l = 1; l <= N; l++) {
        for (int r = l; r <= N; r++) {
            int lx = xc[l].x, ly = xc[l].y, rx = xc[r].x, ry = xc[r].y;
            long long tnum = pre[rx][N] - pre[lx][N] - pre[rx][max(ly, ry)] + pre[lx][max(ly, ry)] + 1;
            long long bnum = pre[rx][min(ly, ry) - 1] - pre[lx][min(ly, ry) - 1] + 1;
            long long num = tnum * bnum;
//            cout << "l: " << l << " r: " << r << " - " << num << '\n';
            ans += num;
        }
    }

    cout << ans + 1 << '\n';
    
    return 0;
}