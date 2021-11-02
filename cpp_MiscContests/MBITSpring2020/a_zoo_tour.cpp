#include "bits/stdc++.h"
using namespace std;

int N, Q;
long long p[100005];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;

    long long total = 0;
    for (int i = 1; i <= N; i++)
    {
        long long ai;
        cin >> ai;
        p[i] = ai + p[i - 1];
        total += ai;
    }

    for (int i = 0; i < Q; i++)
    {
        long long a, b;
        cin >> a >> b;
        if (a > b) swap(a, b);
        a--; b--;

        cout << min(p[b] - (a == 0 ? 0 : p[a]), total - ((p[b] - (a == 0 ? 0 : p[a])))) << '\n';
    }
    return 0;
}