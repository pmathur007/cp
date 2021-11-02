#include "bits/stdc++.h"
using namespace std;

const int MAXN = 505;
const long long MAXD = 1e15;

int N, M, Q;
long long dist[MAXN][MAXN];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    cin >> N >> M >> Q;

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            dist[i][j] = (i == j ? 0 : MAXD);

    for (int i = 0; i < M; i++) {
        int a, b; long long c; cin >> a >> b >> c;
        dist[a - 1][b - 1] = min(dist[a - 1][b - 1], c);
        dist[b - 1][a - 1] = min(dist[b - 1][a - 1], c);
    }

    for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

    for (int i = 0; i < Q; i++) {
        int a, b; cin >> a >> b;
        cout << (dist[a - 1][b - 1] >= MAXD ? -1 : dist[a - 1][b - 1]) << '\n';
    }
    
    return 0;
}