#include "bits/stdc++.h"
using namespace std;

const int MAXN = 10;
int squares[MAXN * MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N; cin >> N;
    int aturn = true;
    int as = 0, bs = 0;

    for (int i = 0; i < 2 * N * N - 2 * N; i++) {
        int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
        x1--; y1--; x2--; y2--;
        if (aturn) cout << "A";
        else cout << "B";

        if (x1 == x2) {
            int captured = 0;
            if (x1 > 0)
                if (++squares[min(y1, y2) * (N - 1) + (x1 - 1)] == 4)
                    captured++;
            if (x1 < N - 1)
                if (++squares[min(y1, y2) * (N - 1) + x1] == 4)
                    captured++;
            if (captured > 0) {
                as += aturn ? captured : 0;
                bs += !aturn ? captured : 0;
            }
            else aturn = !aturn;
        }
        else {
            int captured = 0;
            if (y1 > 0)
                if (++squares[(y1 - 1) * (N - 1) + min(x1, x2)] == 4)
                    captured++;
            if (y1 < N - 1)
                if (++squares[y1 * (N - 1) + min(x1, x2)] == 4)
                    captured++;
            if (captured > 0) {
                as += aturn ? captured : 0;
                bs += !aturn ? captured : 0;
            }
            else aturn = !aturn;
        }
    }

    cout << "\n";
    cout << as << " " << bs << "\n";

    return 0;
}