#include "bits/stdc++.h"
using namespace std;

#define f first
#define s second

const int MAXN = 1e5 + 5;

int N;
long long K, M;
int p[MAXN], ip[MAXN];
vector<pair<int, int>> c[MAXN];
bool visited[MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> K >> M;
    for (int i = 1; i <= N; i++) p[i] = i;

    for (int k = 1; k <= K; k++) {
        int a, b; cin >> a >> b;
        c[p[a]].push_back({k, b});
        c[p[b]].push_back({k, a});
        swap(p[a], p[b]);
    }

    for (int i = 1; i <= N; i++) ip[p[i]] = i;

    vector<int> ans(N + 1);
    unordered_set<int> seen;
    vector<int> cur;
    for (int i = 1; i <= N; i++) {
        if (!visited[i]) {
            int len = 0;
            int k = i;
            while (!visited[k]) {
                visited[k] = true;
                seen.insert(k);
                for (auto &cp : c[k]) seen.insert(cp.s);
                cur.push_back(k);
                len++;
                k = p[k];
            }

            if (len * K <= M) {
                for (auto &ci : cur) 
                    ans[ci] = seen.size();
            }
            else {
                typedef pair<long long, int> T; priority_queue<T, vector<T>, greater<T>> pq;
                priority_queue<T, vector<T>, greater<T>> sq;

                k = i;
                sq.push({0, i});
                sq.push({len * K, i});
                for (int j = 0; j < len; j++) {
                    pq.push({j * K, k});
                    pq.push({j * K + M, k});
                    for (auto &cp : c[k]) {
                        sq.push({j * K + cp.f, cp.s});
                        sq.push({(j + len) * K + cp.f, cp.s});
                    }
                    k = ip[k];
                }

                k = i;
                vector<bool> open(N + 1);
                vector<int> cnt(N + 1);
                queue<T> oq;
                int u = 0;                
                while (!pq.empty()) {
                    T mark = pq.top(); pq.pop();
                    while (!sq.empty() && sq.top().f <= mark.f) {
                        if (cnt[sq.top().s] == 0) u++;
                        cnt[sq.top().s]++;
                        oq.push(sq.top());
                        sq.pop();
                    }

                    if (!open[mark.s]) open[mark.s] = true;
                    else {
                        while (!oq.empty() && oq.front().f < mark.f - M) {
                            cnt[oq.front().s]--;
                            if (cnt[oq.front().s] == 0) u--;
                            oq.pop();
                        }
                        ans[mark.s] = u;
                        if (cnt[mark.s] == 0) ans[mark.s]++;
                    }
                }
            }

            cur.clear(); seen.clear();
        }
    }

    for (int i = 1; i <= N; i++) cout << ans[i] << '\n';

    return 0;
}