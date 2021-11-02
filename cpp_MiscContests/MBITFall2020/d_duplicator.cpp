#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    long N; cin >> N;
    vector<long> a(N), b(N);
    for (int i = 0; i < N; i++) cin >> a[i];
    for (int i = 0; i < N; i++) cin >> b[i];

    vector<long> act(N + 1, 0), bct(N + 1, 0);
    long an = 0, bn = 0;
    for (int i = 0; i < N; i++)
    {
        an += act[a[i]]; act[a[i]]++;
        bn += bct[b[i]]; bct[b[i]]++;
    }

    unordered_map<string, long> mp;
    long abn = 0;
    for (int i = 0; i < N; i++)
    {
        string s = to_string(a[i]) + " " + to_string(b[i]);
        if (mp.find(s) == mp.end()) mp[s] = 1;
        else
        {
            abn += mp[s];
            mp[s]++;
        }
    }

    cout << ((N * (N - 1)) / 2) - (an + bn - abn) << '\n';

    return 0;
}