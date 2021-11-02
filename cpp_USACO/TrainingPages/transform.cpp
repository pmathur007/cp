/*
ID: pranavm7
TASK: transform
LANG: C++
*/

#include "bits/stdc++.h"
using namespace std;

int N;
char A[10][10];
char B[10][10];

bool t1(const char a[10][10], const char b[10][10])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (a[i][j] != b[j][N - i - 1])
                return false;
    return true;
}

bool t2(const char a[10][10], const char b[10][10])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (a[i][j] != b[N - i - 1][N - j - 1])
                return false;
    return true;
}

bool t3(const char a[10][10], const char b[10][10])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (a[i][j] != b[N - j - 1][i])
                return false;
    return true;
}

bool t4(const char a[10][10], const char b[10][10])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (a[i][j] != b[i][N - j - 1])
                return false;
    return true;
}

bool t5(const char a[10][10], const char b[10][10])
{
    char temp[10][10];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            temp[i][j] = a[i][N - j - 1];
    return t1(temp, b) || t2(temp, b) || t3(temp, b);
}

bool t6(const char a[10][10], const char b[10][10])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (a[i][j] != b[i][j])
                return false;
    return true;
}

int main()
{
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    freopen("transform.in", "r", stdin);
    freopen("transform.out", "w", stdout);

    cin >> N;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> A[i][j];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> B[i][j];

    if (t1(A, B))
        cout << "1\n";
    else if (t2(A, B))
        cout << "2\n";
    else if (t3(A, B))
        cout << "3\n";
    else if (t4(A, B))
        cout << "4\n";
    else if (t5(A, B))
        cout << "5\n";
    else if (t6(A, B))
        cout << "6\n";
    else
        cout << "7\n";

    return 0;
}