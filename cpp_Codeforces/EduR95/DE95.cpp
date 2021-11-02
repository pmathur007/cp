#include "bits/stdc++.h"
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N, Q; cin >> N >> Q;
    set<int> p;
    for (int i = 0; i < N; i++)
    {
        int pi; cin >> pi;
        p.insert(pi);
    }

    multiset<int> g;
    int last = -1;
    for (auto &pi : p)
    {
        if (last != -1)
            g.insert(pi - last);
        last = pi;
    }

    if (p.size() <= 2)
        cout << 0 << '\n';
    else
        cout << *p.rbegin() - *p.begin() - *g.rbegin() << '\n';

    for (int q = 0; q < Q; q++)
    {
        int t, x; cin >> t >> x;
        if (t)
        {
            auto ret = p.insert(x);
            auto itr = ret.first;
            if (next(itr) != p.end())
                g.insert(*next(itr) - x);
            if (itr != p.begin())
                g.insert(x - *prev(itr));
            if (next(itr) != p.end() && itr != p.begin())
                g.erase(g.find(*next(itr) - *prev(itr)));
        }
        else
        {
            auto itr = p.find(x);
            if (next(itr) != p.end())
                g.erase(g.find(*next(itr) - *itr));
            if (itr != p.begin())
                g.erase(g.find(*itr - *prev(itr)));
            if (next(itr) != p.end() && itr != p.begin())
                g.insert(*next(itr) - *prev(itr));
            p.erase(x);
        }

        if (p.size() <= 2)
            cout << 0 << '\n';
        else
            cout << *p.rbegin() - *p.begin() - *g.rbegin() << '\n';
    }

    return 0;
}