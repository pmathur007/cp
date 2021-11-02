#include "bits/stdc++.h"
using namespace std;

int rb = 0, rs = 0, rc = 0;
int nb, ns, nc;
int pb, ps, pc;
long long d;

bool good(long long n)
{
    long long c = 0;
    c += max((n * rb) - nb, 0ll) * pb;
    c += max((n * rs) - ns, 0ll) * ps;
    c += max((n * rc) - nc, 0ll) * pc;
    return c <= d;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string s; cin >> s;
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == 'B') rb++;
        else if (s[i] == 'S') rs++;
        else rc++;
    }

    cin >> nb >> ns >> nc >> pb >> ps >> pc; cin >> d;

    long long l = 0, r = 1;
    while (good(r)) r *= 2;

    while (l + 1 < r)
    {
        long long mid = (l + r) / 2;
        if (good(mid)) l = mid;
        else r = mid;
    }

    cout << l << '\n';

    return 0;
}