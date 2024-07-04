# Tic-Tac-Toe Game Project

This project is a Java-based implementation of the classic Tic-Tac-Toe game, featuring both human and AI (bot) players. The game supports different difficulty levels for bots and utilizes various winning strategies to determine the game outcome. It's designed with extensibility in mind, allowing for easy addition of new strategies and bot behaviors.

## Features

- **Customizable Game Board**: Configure the game board size to your preference.
- **Player Types**: Support for human players and AI bots with adjustable difficulty levels (Easy, Medium, Hard).
- **Winning Strategies**: Includes row, column, and diagonal winning strategies.
- **Game State Management**: Tracks the game state, including in-progress, draw, or ended states.
- **Extensible Design**: Easily add new bot strategies or winning conditions.

## Getting Started

To run the game, ensure you have Java installed on your system. Clone the repository, navigate to the project directory, and compile the Java files. Run `Main.java` to start the game.

```bash
git clone <repository-url>
cd Tic-Tac-Toe
javac src/*.java
java src/Main
```
## How to Play

The game is played on a grid that's 3 squares by 3 squares. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares. The first player to get 3 of their marks in a row (up, down, across, or diagonally) is the winner. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a draw.

## Project Structure

- `src/Main.java`: Entry point of the application.
- `src/Controllers/GameController.java`: Handles game logic and flow.
- `src/Models`: Contains the models like Game, Player, Bot, and Symbol.
- `src/Factory`: Factory classes for creating bot strategies based on difficulty.
- `src/Strategy`: Strategies for winning conditions and bot playing behaviors.
- `src/Exceptions`: Custom exceptions for game validation.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any bugs or feature requests.
