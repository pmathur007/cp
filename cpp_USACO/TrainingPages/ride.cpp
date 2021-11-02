/*
ID: pranavm7
TASK: ride
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

long long cometVal()
{
    long long comet = 1;
    string s;
    cin >> s;

    for (auto &c : s)
        comet *= static_cast<long long>(c) - 64;

    return comet;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("ride.in", "r", stdin);
    freopen("ride.out", "w", stdout);

    long long comet1 = cometVal();
    long long comet2 = cometVal();

    if (comet1 % 47 == comet2 % 47)
        cout << "GO" << "\n";
    else
        cout << "STAY" << "\n";

    return 0;
}
