// This program plays you in a game of Rock/Paper/Scissors
#include <iostream>
#include <cstdlib>
#include <limits>
#include <ctime>

using namespace std;

#define win Congratulations, you won!
#define loose Sorry, you lost. Please try again.

int main(){
    int choice;
    int i;
    int comp;
    char res;

    unsigned seed;

    cout << "Rock/Paper/scissors is a simple game. The rules are: \n Rock beats scissors. \n Scissors beats Paper. \n Paper beats rock.\n";
    //The choices
    cout << "Game Choices.\n\n";
    cout << "1. Rock\n";
    cout << "2. Paper\n";
    cout << "3. Scissors\n";
    cout << "4. Quit, exits the game.\n\n";
    cout << "Please enter your choice.";
    cin >> choice;

    //Now for the IF/Else statements.
    if (choice == 1) { //Rock
        cout << "You picked Rock.\n";
        cout << "Now here was my choice.\n";
    } else if (choice == 2) {//Paper
        cout << "You picked Paper.\n";
        cout << "Now here was my choice.\n";
    } else if (choice == 3) {   //Scissors
        cout << "You picked Scissors.\n";
        cout << "Now here was my choice.\n";
    } else if (choice == 4) {
        return 0;
    }
    //*************************************************
    seed = time(0);
    srand(seed); //For the random generator.

    comp = rand() % 3 + 1; //Computers pick.
    if (comp == 1) { //Computer rock
        res = 1;
        cout << "Rock!\n";
    } else if (comp == 2) { //Computer paper
        res = 2;
        cout << "Paper!\n";
    } else if (comp == 3)  { // Computer scissors
        res = 3;
        cout << "Scissors!\n";
    }

    switch (res) {
    case '1': cout << "test";
    case '2': cout << "tests";
    case '3': cout << "test";
    }

    system("pause");
    return 0;
}
