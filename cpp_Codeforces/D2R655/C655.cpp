#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T;
    cin >> T;
    for (int t = 0; t < T; t++)
    {
        int N;
        cin >> N;

        int numRuns = 0, curRun = 0;
        for (int i = 1; i <= N; i++)
        {
            int ai;
            cin >> ai;
            if (ai != i)
            {
                if (curRun == 0) numRuns++;
                curRun++;
            }
            else
                curRun = 0;
        }
        if (numRuns == 0) cout << 0 << '\n';
        else if (numRuns == 1) cout << 1 << '\n';
        else cout << 2 << '\n';
    }

    return 0;
}