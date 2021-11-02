#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N; cin >> N;
        string str; cin >> str;

        vector<long long> s(N + 1);
        map<long long, long long> count;

        s[0] = 0;
        count[0] = 1;

        long long ans = 0;
        for (int i = 1; i <= N; i++)
        {
            long long a = str[i - 1] - '0';
            s[i] = s[i - 1] + a - 1;

            if (count.count(s[i]))
            {
                ans += count[s[i]];
                count[s[i]]++;
            }
            else
            {
                count[s[i]] = 1;
            }
        }
        cout << ans << '\n';
    }

    return 0;
}