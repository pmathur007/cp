#include "bits/stdc++.h"
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    string s; cin >> s;
    string ans;
    for (int i = 0; i < s.length(); i += 3) ans += s[i];
    cout << ans << "\n";
    
    return 0;
}