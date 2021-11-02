#include "bits/stdc++.h"
using namespace std;

struct query
{
    int i, qi, s, x;
};

int N, Q;
int a[100005];
bool active[100005];

vector<int> last;
set<query> qs;

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> Q;
    for (int i = 0; i < N; i++) cin >> a[i];

    for (int i = 0; i < Q; i++)
    {
        int l, r, x;
        query ql, qr;

        cin >> l >> r >> x;
        ql = {l - 1, i, 1, x}; qr = {r, i, 0, x};
        qs.insert(ql); qs.insert(qr);
    }

    int numActive = 0;
    for (int i = 0; i < N; i++)
    {
        while ((*qs.begin()).i <= i)
        {
            query q = *qs.begin();
            qs.erase(qs.begin());

            if (q.s)
            {
            }
        }
    }

    return 0;
}