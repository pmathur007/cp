/*
ID: pranavm7
TASK: beads
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("beads.in", "r", stdin);
    freopen("beads.out", "w", stdout);

    int N;
    string necklace;
    cin >> N;
    cin >> necklace;

    if (necklace.find('b') == string::npos || necklace.find('r') == string::npos) cout << N << "\n";
    else
    {
        int maxAns = 0;
        for (int i = 0; i < N; i++)
        {
            int b = 0;
            int j;

            for (j = i; necklace[j] == 'w'; j = (j + N + 1) % N)
                b++;
            char rcolor = necklace[j];
            for (; necklace[j] == rcolor || necklace[j] == 'w'; j = (j + N + 1) % N)
                b++;

            for (j = (i - 1) % N; necklace[j] == 'w'; j = (j + N - 1) % N)
                b++;
            char lcolor = necklace[j];
            for (; necklace[j] == lcolor || necklace[j] == 'w'; j = (j + N - 1) % N)
                b++;

            maxAns = max(maxAns, b);
        }
        cout << maxAns << "\n";
    }

    return 0;
}