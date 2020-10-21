#include <iostream>
#include <cstring>
using namespace std;

int main() {

    char word[100];
    cin.getline(word, 100);

    char *p;
    p = strtok(word, ".");

    while(p) {
        cout << p << endl;
        p = strtok(NULL, ".");
    }

    return 0;
}