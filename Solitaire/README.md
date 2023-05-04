# Solitaire Game in Java 

This project is a command-line version of the popular Solitaire game. It is written in Java and runs in the console.

## How to Play

1. Compile the Java files and launch the game by running the command ``make`` in the Solitaire/src directory.

When the game starts, you will see the tableau, which is where you will play your cards. The tableau is made up of seven piles, with the first pile containing one card, the second pile containing two cards, the third pile containing three cards, and so on.

The objective of the game is to move all the cards to the foundation piles, which are located at the top right of the tableau. There are four foundation piles, one for each suit, and you need to place the cards in ascending order, starting with the Ace and ending with the King.
To move cards, you can use the command line interface. You will be prompted to enter the source pile and the destination pile. You can move one card at a time, and there are certain rules to follow:
* You can move a card to a foundation pile if it is the next card in the sequence and it is the same suit as the pile.
* You can move a card to a tableau pile if it is the opposite color and one rank lower than the top card of the destination pile.
* You can move a group of cards from one tableau pile to another tableau pile if the group is in descending order and alternating colors.

## Dependencies
This project does not have any external dependencies. It was developed using Java 8.

## Authors
* Samy YACEF
* Casin0R0yal Group

## Acknowledgments
* This project was inspired by the Solitaire game built into Windows.
