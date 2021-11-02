#include "bits/stdc++.h"
using namespace std;

int N;
int a[1005];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; i++) cin >> a[i];

    int numPoles = 0;
    int cur = 0;
    while (cur != N - 1)
    {
        numPoles++;
        double maxSlope = -10000000;
        int next = 0;

        for (int i = cur + 1; i < N; i++)
        {
            if (((double) (a[i] - a[cur])) / (i - cur) >= maxSlope) next = i;
            maxSlope = max(maxSlope, ((double) (a[i] - a[cur])) / (i - cur));
        }

        cur = next;
    }

    cout << numPoles + 1 << '\n';

    return 0;
}