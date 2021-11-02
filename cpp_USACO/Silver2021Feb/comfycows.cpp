#include "bits/stdc++.h"
using namespace std;

const int A = 501;
const int dx[]{1, 0, -1, 0}, dy[]{0, 1, 0, -1};

int N;
bool hasCow[2005][2005];

bool isComfy(int x, int y) {
    if (!hasCow[x][y]) return false;
    int numAround = hasCow[x-1][y] + hasCow[x+1][y] + hasCow[x][y-1] + hasCow[x][y+1];
    return numAround == 3;
}

int placeCow(int x, int y);

int fixCow(int x, int y) {
    for (int d = 0; d < 4; d++)
        if (!hasCow[x+dx[d]][y+dy[d]])
            return 1 + placeCow(x+dx[d], y+dy[d]);
    return 0;
}

int placeCow(int x, int y) {
    hasCow[x][y] = true;
    int ans = 0;
    if (isComfy(x, y))
        ans += fixCow(x, y);
    for (int d = 0; d < 4; d++)
        if (isComfy(x+dx[d], y+dy[d]))
            ans += fixCow(x+dx[d], y+dy[d]);
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    int ans = 0;
    while (N--) {
        int x, y; cin >> x >> y;
        x += A, y += A;

        if (hasCow[x][y]) ans--;
        else ans += placeCow(x, y);

        cout << ans << "\n";
    }

    return 0;
}