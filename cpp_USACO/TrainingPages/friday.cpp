/*
ID: pranavm7
TASK: friday
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

int fridays[7];
int months[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

bool isLeap(int year)
{
    if (year % 400 == 0)
        return true;
    if (year % 100 == 0)
        return false;
    return year % 4 == 0;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("friday.in", "r", stdin);
    freopen("friday.out", "w", stdout);

    int N;
    cin >> N;

    int day = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < 12; j++)
        {
            fridays[day]++;
            if (j == 1 && isLeap(1900 + i))
                day = (day + 29) % 7;
            else
                day = (day + months[j]) % 7;
        }
    }

    cout << fridays[0] << " "
        << fridays[1] << " "
        << fridays[2] << " "
        << fridays[3] << " "
        << fridays[4] << " "
        << fridays[5] << " "
        << fridays[6] << "\n";

    return 0;
}