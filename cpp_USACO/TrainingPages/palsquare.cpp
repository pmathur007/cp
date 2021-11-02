/*
ID: pranavm7
TASK: palsquare
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

char digits[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

string toBase(int n, int b)
{
    string nb = "";
    while (n > 0)
    {
        nb = digits[n % b] + nb;
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
    freopen("palsquare.in", "r", stdin);
    freopen("palsquare.out", "w", stdout);

    int B;
    cin >> B;

    for (int i = 1; i <= 300; i++)
    {
        if (isPalindrome(toBase(i * i, B)))
            cout << toBase(i, B) << " " << toBase(i * i, B) << "\n";
    }

    return 0;
}