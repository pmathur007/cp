#include "bits/stdc++.h"
using namespace std;

int N;
int a[105];
bool l[105];

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int T; cin >> T;
    while (T--)
    {
        cin >> N;
        for (int i = 1; i <= N; i++) cin >> a[i];
        for (int i = 1; i <= N; i++) cin >> l[i];

        vector<int> ul;
        for (int i = 0; i < N; i++)
            if (!l[i])
                ul.push_back(a[i]);
        sort(ul.begin(), ul.end());

        vector<int> na(N);
        int li = 0, ri = ul.size() - 1, p = 0;
        bool done = false;
        for (int i = 0; i < N; i++)
        {
            if (l[i])
            {
                p += a[i];
                na[i] = a[i];
            }
            else
            {

            }
        }
    }

    return 0;
}