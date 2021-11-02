/*
ID: pranavm7
TASK: gift1
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

int N;
string *names = new string[10];
int *money = new int[10];

int findName(const string &name)
{
    for (int i = 0; i < N; i++)
    {
        if (names[i] == name)
            return i;
    }
    return -1;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("gift1.in", "r", stdin);
    freopen("gift1.out", "w", stdout);

    cin >> N;

    for (int i = 0; i < N; i++) cin >> names[i];

    for (int i = 0; i < N; i++)
    {
        string gName;
        cin >> gName;
        int gi = findName(gName);

        int m, nr;
        cin >> m >> nr;
        if (nr != 0)
        {
            money[gi] -= (m - (m % nr));

            for (int j = 0; j < nr; j++)
            {
                string rName;
                cin >> rName;
                int ri = findName(rName);
                money[ri] += m / nr;
            }
        }
    }

    for (int i = 0; i < N; i++)
        cout << names[i] << " " << money[i] << "\n";

    return 0;
}
