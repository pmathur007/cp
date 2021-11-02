#include "bits/stdc++.h"
using namespace std;

#define f first
#define str second

int N;
vector<int> lefts, rights;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int si, di;
        cin >> si >> di;

        if (di)
            rights.push_back(si);
        else
        {
            while (!rights.empty() && si > 0)
            {
                if (rights.back() < si)
                {
                    si -= rights.back();
                    rights.pop_back();
                }
                else
                {
                    rights.back() -= si;
                    si = 0;
                }
            }

            if (rights.empty())
            {
                lefts.push_back(si);
            }
        }
    }

    cout << size(lefts) + size(rights) << '\n';

    for (auto &i : lefts)
        cout << i << ' ' << 0 << '\n';
    for (auto &i : rights)
        cout << i << ' ' << 1 << '\n';

    return 0;
}