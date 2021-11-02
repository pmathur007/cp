#include "bits/stdc++.h"
using namespace std;

long long pow3[40];

bool check(int x, int y) {
    for (int k = 1; k <= max(x, y); k *= 3) {
        if (((x / k) % 3) % 2 != ((y / k) % 3) % 2) {
            return false;
        }
    }
    return true;
}

long long numInPeriod(long long x, int p) {
    if (x % 2 == 1) return 0;
    if (p == 0) return 1;

    long long d = (pow3[p] - pow3[p - 1]) / 3;
    if (x < pow3[p - 1] + d) return numInPeriod(x - d, p - 1);
    if (x >= pow3[p - 1] + 2*d) return numInPeriod(x - 3*d, p - 1);
    return 3 * numInPeriod(x - 2*d, p - 1);
}

long long numInPeriod(long long x) {
    int p = 0;
    while (x >= pow3[p]) p++;
    return numInPeriod(x, p);
}

long long partialPeriod(long long x, long long i, int p) {
    cout << x << " " << i << " " << p << " ";
    if (x == 1) return 1;

    if (i >= pow3[p - 1])
        i = pow3[p - 1] - 1;
    long long d = (pow3[p] - pow3[p - 1]) / 3;
    cout << d << '\n';
    if (x < pow3[p - 1] + d) {
        if (i < 2 * pow3[p - 2]) return 0;
        else return partialPeriod(x - d, i - 2 * pow3[p - 2], p - 1);
    }
    else if (x >= pow3[p - 1] + 2*d) {
        return partialPeriod(x - 3*d, i, p - 1);
    }
    else {
        long long n = numInPeriod(x);
        if (i < pow3[p - 2]) return partialPeriod(x - 2*d, i, p - 1);
        else if (i < 2 * pow3[p - 2]) return n / 3 + partialPeriod(x - 2*d, i - pow3[p - 2], p - 1);
        return 2 * (n / 3) + partialPeriod(x - 2*d, i - 2*pow3[p - 2], p - 1);
    }
}

long long partialPeriod(long long x, long long i) {
    int p = 0;
    while (x >= pow3[p]) p++;
    return partialPeriod(x, i, p);
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    pow3[0] = 1;
    for (int i = 1; i <= 39; i++) pow3[i] = pow3[i - 1] * 3;

//    int Q; cin >> Q;
//    while (Q--) {
//        long long d, x, y; cin >> d >> x >> y;
//        if (d == 1000000000000000000) {
//            cout << 1000000000000000001 << '\n';
//            continue;
//        }
//
//        long long ans = 0;
//        for (int di = 0; di <= d; di++) {
//            long long xi = x + di, yi = y + di;
//            bool counts = true;
//            for (long long k = 1; k <= max(xi, yi); k *= 3) {
//                if ((((xi / k) % 3) - ((yi / k) % 3)) % 2 != 0) {
//                    counts = false;
//                    break;
//                }
//            }
//            if (counts) ans++;
//        }
//        cout << ans << '\n';
//    }

//     int k = 1;
//     for (int i = 0; i < 81; i++) {
//         if (i % 1 == 0) {
//             if (i >= k) k *= 3;
//             cout << i << ": ";
//             for (int j = 0; j < k; j++)
//                 cout << (check(i + j, j) ? "1" : "_");
// //            cout << " " << partialPeriod(i, 5) << '\n';
//             cout << '\n';
//         }
//     }

    // cout << partialPeriod(18, 6);

   int N = 81;
   for (int x = 0; x < N; x++) {
       for (int y = 0; y < N; y++) {
           bool printed = false;
           for (int k = 1; k <= max(x, y); k *= 3) {
               if (((x / k) % 3) % 2 != ((y / k) % 3) % 2) {
                   cout << '.';
                   printed = true;
                   break;
               }
           }
           if (!printed) cout << 'O';
       }
       cout << '\n';
   }
    
    return 0;
}