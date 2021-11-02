#include "bits/stdc++.h"
using namespace std;

const int MAXN = 1e5 + 5;

int N, K;
int p[MAXN];
unordered_set<int> c[MAXN];
bool visited[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) p[i] = i;
    for (int i = 0; i < K; i++) {
        int ai, bi; cin >> ai >> bi;
        c[p[ai]].insert(bi);
        c[p[bi]].insert(ai);
        swap(p[ai], p[bi]);
    }

    vector<int> ans(N + 1);
    vector<int> cur;
    unordered_set<int> seen;
    for (int i = 1; i <= N; i++) {
        if (!visited[i]) {
            int k = i;
            while (!visited[k]) {
                visited[k] = true;
                seen.insert(k);
                seen.insert(c[k].begin(), c[k].end());
                cur.push_back(k);
                k = p[k];
            }

            for (auto &ki : cur) ans[ki] = seen.size();
            cur.clear(); seen.clear();
        }
    }

    for (int i = 1; i <= N; i++) cout << ans[i] << '\n';

    return 0;
}