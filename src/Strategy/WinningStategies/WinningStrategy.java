package Strategy.WinningStategies;

import Models.Board;
import Models.Move;
import Models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
