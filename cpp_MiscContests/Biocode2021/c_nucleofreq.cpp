#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int N; cin >> N;
    string s; cin >> s;

    map<char, int> f;
    for (auto &c : s) f[c]++;

    char maxC = 'A';
    if (f['C'] > f[maxC]) maxC = 'C';
    if (f['G'] > f[maxC]) maxC = 'G';
    if (f['T'] > f[maxC]) maxC = 'T';
    cout << maxC << "\n";
    
    return 0;
}