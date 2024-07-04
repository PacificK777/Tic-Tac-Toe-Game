import Controllers.GameController;
import Exceptions.InvalidBotCountException;
import Exceptions.InvalidMoveException;
import Exceptions.InvalidPlayerCountException;
import Exceptions.InvalidPlayerSymbolException;
import Models.*;
import Strategy.WinningStategies.ColumnWinningStrategies;
import Strategy.WinningStategies.DiagonalWinningStrategies;
import Strategy.WinningStategies.RowWinningStrategies;
import Strategy.WinningStategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException, InvalidMoveException {
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Mayank", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Player("DS",new Symbol('O'), PlayerType.HUMAN));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategies(),
                new ColumnWinningStrategies(),
                new DiagonalWinningStrategies());

        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension,players,winningStrategies);



        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            //1. Print the board
            //2. Ask user to choose the cell, where they want to make the move
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

            gameController.handleGameEnd(game);
    }
}
