#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, Q; cin >> N >> Q;
    vector<vector<int>> p(4, vector<int>(N + 1, 0));
    for (int i = 1; i <= N; i++) {
        int k; cin >> k;
        for (int j = 1; j <= 3; j++) p[j][i] = p[j][i - 1] + (j == k);
    }

    for (int i = 0; i < Q; i++) {
        int A, B; cin >> A >> B;
        cout << p[1][B] - p[1][A - 1] << " " << p[2][B] - p[2][A - 1] << " " << p[3][B] - p[3][A - 1] << "\n";
    }
    
    return 0;
}