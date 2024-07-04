package Strategy.WinningStategies;

import Models.Board;
import Models.Move;
import Models.Player;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategies implements WinningStrategy{

    private Map<Integer, Map<Symbol,Integer>> rowCounts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        Symbol symbol= move.getPlayer().getSymbol();;

        if(!rowCounts.containsKey(row)){
            rowCounts.put(row, new HashMap<>());
        }

        Map<Symbol,Integer> rowMap = rowCounts.get(row);

        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol)+1);

        if(rowMap.get(symbol).equals(board.getDimension())){
            return true;
        }
        return false;
    }
}
