#include "bits/stdc++.h"
using namespace std;

const int MAXN = 100005;

int N;
int sz[MAXN];
vector<int> graph[MAXN], cpaths[MAXN];

void dfs(int v, int p) {
    sz[v] = 1;
    for (auto &c : graph[v]) if (c != p) {
        dfs(c, v);
        sz[v] += sz[c];
        cpaths[v].push_back(sz[c]);
    }
    if (sz[v] != N) cpaths[v].push_back(N - sz[v]);
}

bool works(int k) {
    if ((N - 1) % k != 0) return false;

    vector<int> hs(N, 0);
    for (int v = 0; v < N; v++)
    {
        int unpaired = 0;
        for (auto &c : cpaths[v]) {
            if (c % k == 0) continue;
            if (hs[k - (c % k)]) hs[k - (c % k)]--, unpaired--;
            else hs[c % k]++, unpaired++;
        }
        if (unpaired) return false;
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("deleg.in", "r", stdin);
    freopen("deleg.out", "w", stdout);

    cin >> N;
    for (int i = 0; i < N - 1; i++) {
        int a, b; cin >> a >> b;
        graph[a - 1].push_back(b - 1);
        graph[b - 1].push_back(a - 1);
    }

    dfs(0, -1);
    for (int i = 1; i < N; i++) {
        if (works(i)) cout << "1";
        else cout << "0";
    }

    cout << "\n";

    return 0;
}