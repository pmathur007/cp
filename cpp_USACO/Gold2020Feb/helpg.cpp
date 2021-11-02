#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("help.in", "r", stdin);
    freopen("help.out", "w", stdout);

    const long M = 1e9 + 7;
    int N; cin >> N;
    set<pair<int, bool>> q;
    for (int i = 0; i < N; i++)
    {
        int a, b; cin >> a >> b;
        q.insert({a, true});
        q.insert({b, false});
    }

    vector<long> pow2(N + 1);
    pow2[0] = 1;
    for (int i = 1; i <= N; i++) pow2[i] = (2 * pow2[i - 1]) % M;

    int open = 0;
    long ans = 0;
    for (int i = 0; i < 2*N; i++)
    {
        pair<int, bool> p = *q.begin();
        q.erase(*q.begin());

        if (p.s)
            ans = (ans + pow2[N - 1 - open++]) % M;
        else open--;
    }

    cout << ans << '\n';

    return 0;
}