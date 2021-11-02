/*
ID: pranavm7
TASK: dualpal
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

string toBase(int n, int b)
{
    string nb = "";
    while (n > 0)
    {
        nb = to_string(n % b) + nb;
        n /= b;
    }
    return nb;
}

bool isPalindrome(string n)
{
    for (int i = 0; i < n.length(); i++)
        if (n[i] != n[n.length() - i - 1])
            return false;
    return true;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("dualpal.in", "r", stdin);
    freopen("dualpal.out", "w", stdout);

    int N, S;
    cin >> N >> S;

    for (int i = S + 1; N > 0; i++)
    {
        int numPal = isPalindrome(to_string(i));
        for (int b = 2; b <= 9; b++)
        {
            if (isPalindrome(toBase(i, b)))
            {
                numPal++;
                if (numPal == 2)
                {
                    N--;
                    cout << i << "\n";
                    continue;
                }
            }
        }
    }

    return 0;
}