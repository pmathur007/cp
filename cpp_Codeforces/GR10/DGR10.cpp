#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    for (int t = 0; t < T; t++)
    {
        int N; cin >> N;
        string s; cin >> s;

        if (s.find('L') == string::npos || s.find('R') == string::npos)
            cout << s.length() / 3 + (s.length() % 3 == 0 ? 0 : 1);
        else
        {
            int si = 0;
            while (s[si] == s[0]) si = (si + 1) % N;

            int ans = 0, ls = s[si] == 'L', rs = s[si] == 'R';
            for (int i = (si + 1) % N; i != si; i = (i + 1) % N)
            {
                if (s[i] == 'L')
                {
                    ls++;
                    if (rs >= 3) ans += rs / 3;
                    rs = 0;
                }
                else
                {
                    rs++;
                    if (ls >= 3) ans += ls / 3;
                    ls = 0;
                }
            }
            if (ls > 0) ans += ls / 3;
            if (rs > 0) ans += rs / 3;

            cout << ans << '\n';
        }
    }

    return 0;
}