#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N; cin >> N;
    while (N--) {
        int x; cin >> x;
        int ans = 0;
        for (int i = 1; i <= floor(sqrt(x)); i++)
            if (x % i == 0)
                ans++;
        cout << 2 * ans + (sqrt(x) == floor(sqrt(x)) ? -1 : 0) << '\n';
    }
    
    return 0;
}