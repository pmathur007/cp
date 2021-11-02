#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 5e4 + 5, MAXK = 55;
const long long MAXD = 10000000000005;

int N, K;
int b[MAXN];
vector<int> cowsOfBreed[MAXK], canSend[MAXK];
long long dist[MAXN];
bool visited[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> K;
    for (int i = 0; i < N; i++) {
        cin >> b[i];
        b[i]--;
        cowsOfBreed[b[i]].push_back(i);
    }

    for (int i = 0; i < K; i++) {
        for (int j = 0; j < K; j++) {
            char c; cin >> c;
            if (c == '1') canSend[i].push_back(j);
        }
    }

    set<pair<int, int>> pq;
    bool found = false;
    pq.insert({0, 0});
    fill(dist, dist + N, MAXD);
    dist[0] = 0;


    while (!pq.empty()) {
        pair<int, int> v = *pq.begin(); pq.erase(pq.begin());

        if (v.s == N - 1) {
            cout << v.f << "\n";
            found = true;
            break;
        }

        if (visited[v.s]) continue;
        visited[v.s] = true;

        for (auto &bi : canSend[b[v.s]]) {
            for (auto c : cowsOfBreed[bi]) {
                if (!visited[c] && dist[v.s] + abs(v.s - c) < dist[c]) {
                    dist[c] = dist[v.s] + abs(v.s - c);
                    pq.insert({dist[c], c});
                }
            }
        }
    }

    if (!found) cout << "-1\n";
    
    return 0;
}