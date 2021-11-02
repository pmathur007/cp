#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++) {
        int N; cin >> N;
        vector<bool> in(2 * N);
        for (int i = 0; i < N; i++) {
            int bn; cin >> bn;
            in[bn - 1] = true;
        }

        int maxx = 0, minx = N;

        int bp = 0, np = 0;
        while (!in[bp]) bp++;
        while (np < 2 * N && (np < bp || in[np])) np++;
        while (bp < 2 * N && np < 2 * N) {
            maxx++; bp++; np++;
            while (bp < 2 * N && !in[bp]) bp++;
            while (np < 2 * N && (np < bp || in[np])) np++;
        }

        bp = 2 * N - 1; np = 2 * N - 1;
        while (!in[bp]) bp--;
        while (np >= 0 && (np > bp || in[np])) np--;
        while (bp >= 0 && np >= 0) {
            minx--; bp--; np--;
            while (bp >= 0 && !in[bp]) bp--;
            while (np >= 0 && (np > bp || in[np])) np--;
        }

        cout << maxx - minx + 1 << '\n';
    }
    
    return 0;
}