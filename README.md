# Talabia Chess

Talabia Chess is a customized chess game developed in Java. It introduces unique rules and gameplay mechanics while maintaining the essence of traditional chess. This project was developed as a programming assignment and is now available for public access.

## Features
- **Custom Chess Pieces**: Includes unique pieces such as Point, Hourglass, Time, Plus, and Sun.
- **Dynamic Piece Transformations**: Time and Plus pieces transform into each other every two turns.
- **7x6 Board Layout**: A smaller chessboard compared to the standard 8x8 setup.
- **Save & Load Game**: The game automatically saves after every move and can be reloaded anytime.
- **Graphical User Interface (GUI)**: Players interact with the game using a user-friendly Java Swing-based GUI.
- **Win Condition**: The game ends when a player's Sun piece is captured.

## Game Rules
1. The objective is to capture the opponent's Sun piece.
2. The yellow side always moves first.
3. Only the Hourglass piece can jump over other pieces.
4. After every two turns, Time pieces transform into Plus pieces, and Plus pieces transform into Time pieces.

## Installation and Running the Game
### Prerequisites
- Java Runtime Environment (JRE) installed.
- Java Development Kit (JDK) if you want to modify the source code.

### Compilation
To compile the Java source files, navigate to the project directory and run:
```sh
javac Main.java
```

### Running the Game
Once compiled, execute the game using:
```sh
java Main
```

## How to Play
1. **Starting the Game**: Choose "Start Game" for a new game or "Load Game" to continue from a previous session.
2. **Moving Pieces**:
   - Click on a piece to select it.
   - Click on a valid tile to move it.
   - If an invalid move is made, an error message will be displayed.
3. **Ending the Game**: The game ends when a player's Sun piece is captured. The winner is then declared.
4. **Saving and Loading**:
   - The game automatically saves after every move.
   - To load a saved game, click "Load Game" from the main menu.

📄 *Refer to `Talabia Chess.pdf` for detailed gameplay and images.*

---

Feel free to contribute or modify the project to improve its functionality! 🚀

