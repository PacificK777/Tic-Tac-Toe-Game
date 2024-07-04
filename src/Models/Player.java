package Models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType){
        this.name = name;
        this.symbol =symbol;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Move makeMove(){
        System.out.println("Please enter the row index where you want to make the move");
        int row = scanner.nextInt();

        System.out.println("Please enter the column index where you want to make the move");
        int col = scanner.nextInt();

        return new Move(this,new Cell(row,col));

    }
}
