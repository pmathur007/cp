#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1005;

int N;
int b[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) cin >> b[i][j];

    int rmax = 0;
    for (int i = 0; i < N; i++) {
        int s1 = 0, s2 = 0;
        for (int j = 0; j < N; j++) {
            if (j % 2 == 0) s1 += b[i][j];
            else s2 += b[i][j];
        }
        rmax += max(s1, s2);
    }

    int cmax = 0;
    for (int j = 0; j < N; j++) {
        int s1 = 0, s2 = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) s1 += b[i][j];
            else s2 += b[i][j];
        }
        cmax += max(s1, s2);
    }

    cout << max(rmax, cmax) << "\n";

    return 0;
}