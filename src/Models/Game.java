package Models;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidMoveException;
import Exceptions.InvalidPlayerCountException;
import Exceptions.InvalidPlayerSymbolException;
import Strategy.WinningStategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard(){
        board.print();
    }

    public boolean validateMove(Move move){
        Player player = move.getPlayer();
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        if(row<0 || row>=board.getDimension() || col<0 || col>= board.getDimension() ||
            !board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }
        return true;
    }

    public void makeMove() throws InvalidMoveException {

        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It's "+currentPlayer.getName()+"'s move");

        //Ask the user where they want to make the move
        Move move = currentPlayer.makeMove(board);

        //Before making the move, validate if the cell is empty or not
        if(! validateMove(move)){
            throw new InvalidMoveException("Invalid move, please try again !!");
        }
        // If valid move, we can execute on the board
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex+1) % players.size();
        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            // Game has drawn
            gameState= GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move){
        // check the row, column and diagonal (if applicable)
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.players = players;
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static class Builder{
        //Things required to start a new game
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        //In builder, only setters are required

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount=0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
            }
            if(botCount>1){
                throw new InvalidBotCountException("Only 1 bot is allowed.");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1){
                throw new InvalidPlayerCountException("Number of players should be 1 less than the size of board");
            }
        }

        private void validateUniquePlayerSymbols() throws InvalidPlayerSymbolException {
//            Set<String> symbols = new HashSet<>();
//            for (Player player : players) {
//                if (!symbols.add(player.getSymbol())) {
//                    throw new InvalidPlayerSymbolException("Two players cannot have the same symbol.");
//                }
//            }
        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerSymbols();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
            // Before building the game we should first validate the game object
            validate();
            return new Game(dimension,players,winningStrategies);
        }
    }
}
