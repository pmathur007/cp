#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    map<int, int> m;
    int s1, s2, s3, s4; cin >> s1 >> s2 >> s3 >> s4;

    int ans = 0;
    if (s2 == s1) ans++;
    if (s3 == s1 || s3 == s2) ans++;
    if (s4 == s1 || s4 == s2 || s4 == s3) ans++;

    cout << ans << '\n';

    return 0;
}