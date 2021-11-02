#include "bits/stdc++.h"
using namespace std;

const int MAXN = 200005;
int N, M;
vector<int> graph[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
//    freopen("fcolor.in", "r", stdin);
//    freopen("fcolor.out", "w", stdout);

    cin >> N >> M;
    queue<int> q;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        graph[a - 1].push_back(b - 1);
        if (graph[a - 1].size() > 1) q.push(a - 1);
    }


    return 0;
}