/*
ID: pranavm7
TASK: milk2
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

int N, S = 1000005, E = 0;
int t[1000005];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("milk2.in", "r", stdin);
    freopen("milk2.out", "w", stdout);

    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int s, e;
        cin >> s >> e;
        S = min(S, s); E = max(E, e);
        t[s]++; t[e]--;
    }

    int cows = 0, maxUnmilked = -1, unmilked = 0, maxMilked = -1, milked = 0;
    for (int i = S; i <= E; i++)
    {
        if (cows == 0)
        {
            if (t[i] != 0)
            {
                maxUnmilked = max(maxUnmilked, unmilked);
                unmilked = 0;
                milked++;
            }
            else
                unmilked++;
        }
        else
        {
            if (cows + t[i] == 0)
            {
                maxMilked = max(maxMilked, milked);
                milked = 0;
                unmilked++;
            }
            else
                milked++;
        }
        cows += t[i];
    }

    cout << maxMilked << " " <<  maxUnmilked << "\n";

    return 0;
}