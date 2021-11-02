#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        string s;
        cin >> s;
        vector<int> ones;

        ones.push_back(0);
        int n = 0;
        for (auto &c : s)
        {
            if (c == '1')
                ones[n]++;
            else
            {
                ones.push_back(0);
                n++;
            }
        }

        sort(ones.begin(), ones.end());
        int ans = 0;
        for (int i = ones.size() - 1; i >= 0; i -= 2) ans += ones[i];
        cout << ans << '\n';
    }

    return 0;
}