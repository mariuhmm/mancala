package mancala;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules {
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
        // This method can be implemented in the abstract class.
        if (pitNum < 1 || pitNum > 12) {
                throw new PitNotFoundException();
        }
        if (pitNum >= 1 && pitNum <= 6) {
            for (int i = 0; i <= 5; i++) {
                if (pitList.get(i).getStoneCount() > 0) {
                    return false;
                }
            }
            return true;
        }
        if (pitNum >= 7) {
            for (int i = 6; i <= 11; i++) {
                if (pitList.get(i).getStoneCount() > 0) {
                    return false;
                }
            }
            return true;
        }
    
        return false; 
    }  

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);
    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {
        // this method can be implemented in the abstract class.
        Store storeOne = new Store();
        Store storeTwo = new Store();
        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/

        setStore(storeOne, one);
        setStore(storeTwo, two);
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        StringBuilder boardString = new StringBuilder();
        boardString.append("\t");
        for (int i = 12; i >= 7; i--) {
            boardString.append(pitList.get(i - 1).getStoneCount()).append("\t");
        }
        boardString.append("\n" + storeList.get(1).getTotalStones()).append("\t\t\t\t\t\t\t");
        boardString.append(storeList.get(0).getTotalStones()).append("\n\t");
        
        for (int i = 1; i <= 6; i++) {
            boardString.append(pitList.get(i - 1).getStoneCount()).append("\t");
        }
        return boardString.toString();
    }
}
