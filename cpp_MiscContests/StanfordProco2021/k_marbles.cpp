#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e6 + 5;

int N, K;
char a[MAXN];
int pre[MAXN], suf[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) cin >> a[i];

    int i = 1, j = N;
    for (int b = 0; b <= K; b++) {
        pre[b] = b == 0 ? 0 : pre[b - 1]; suf[b] = b == 0 ? 0 : suf[b - 1];
        while (a[i] != 'B') {
            pre[b]++; 
            i++; 
        }
        i++;

        while (a[j] != 'B') {
            suf[b]++;
            j--;
        }
        j--;
    }

    int ans = 0;
    for (int i = 0; i <= K; i++)
        ans = max(ans, pre[i] + suf[K - i]);
    cout << ans << "\n";

    return 0;
}