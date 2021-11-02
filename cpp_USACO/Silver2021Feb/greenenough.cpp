#include "bits/stdc++.h"
using namespace std;

const int MAXN = 505;

int N;
int g[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) cin >> g[i][j];

    long long nr100 = 0, nr101 = 0;
    for (int i = 0; i < N; i++) {
        vector<bool> works100(N, true), works101(N, true);
        for (int j = i; j < N; j++) {
            int cur100 = 0, cur101 = 0;
            for (int k = 0; k < N; k++) {
                works100[k] = works100[k] && (g[j][k] >= 100);
                if (works100[k]) nr100 += ++cur100;
                else cur100 = 0;

                works101[k] = works101[k] && (g[j][k] >= 101);
                if (works101[k]) nr101 += ++cur101;
                else cur101 = 0;
            }
        }
    }

    cout << nr100 - nr101 << "\n";

    return 0;
}