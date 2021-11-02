#include "bits/stdc++.h"
using namespace std;

int N, K;
int a[100005];

int lb(int v)
{
    int l = -1, r = N;
    while (l + 1 < r)
    {
        int mid = (l + r) / 2;
        if (a[mid] <= v) l = mid;
        else r = mid;
    }
    return l;
}

int rb(int v)
{
    int l = -1, r = N;
    while (l + 1 < r)
    {
        int mid = (l + r) / 2;
        if (a[mid] < v) l = mid;
        else r = mid;
    }
    return r;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) cin >> a[i];
    sort(a, a + N);

    cin >> K;
    while (K--)
    {
        int ql, qr; cin >> ql >> qr;
        int l = rb(ql), r = lb(qr);
        cout << r - l + 1 << (K == 0 ? '\n' : ' ');
    }

    return 0;
}