#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int n; cin >> n;

    vector<int> home(100005), away(100005);
    for (int i = 0; i < n; i++)
    {
        int h, a; cin >> h, a;
        home[h] += 1;
        away[a] += 1;
    }



    return 0;
}