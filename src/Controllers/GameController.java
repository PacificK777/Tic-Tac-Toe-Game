package Controllers;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidPlayerCountException;
import Exceptions.InvalidPlayerSymbolException;
import Models.Game;
import Models.GameState;
import Models.Player;
import Strategy.WinningStategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategy) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategy)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();


    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();

    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
