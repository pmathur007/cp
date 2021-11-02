#include <iostream>
#include <array>
#include <vector>

struct Student
{
    std::string firstName;
    int grade;
};

int main()
{
    int numStudents{};
    std::cin >> numStudents;

    std::vector<int> students(numStudents);
    for (int i = 0; i < numStudents; i++)
    {
        std::string name{};
        int grade;
        std::cin >> name;
        std::cin >> grade;

        students[i] = {name, grade};
    }
}
