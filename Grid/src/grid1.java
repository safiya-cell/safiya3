import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@SuppressWarnings("serial")
public class grid1 extends JFrame {
    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;

    private JPanel board;

    private static final int DEFAULT_ROWS = 10;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int DEFAULT_BOMBS = 25;

    public grid1() {
    	this(DEFAULT_ROWS,DEFAULT_COLUMNS, DEFAULT_BOMBS);
    	bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        initializeGame();
    }

    public grid1(int rows, int columns) {
    	this.numRows = rows;
        this.numColumns = columns; 
    }

    public grid1(int rows, int columns, int numBombs) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = numBombs;
        
    }

    private void initializeGame() {
        createBombGrid();
        createCountGrid();
        populateBoard();
    }

    public int getNumBombs() {
        return numBombs;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public boolean[][] getBombGrid() {
        boolean[][] newBombGrid = new boolean[bombGrid.length][];
        for (int i = 0; i < bombGrid.length; i++) {
            newBombGrid[i] = new boolean[bombGrid[i].length];
            for (int j = 0; j < bombGrid[i].length; j++) {
                newBombGrid[i][j] = bombGrid[i][j];
            }
        }
        return newBombGrid;
    }

    public int[][] getCountGrid() {
        int[][] newCountGrid = new int[countGrid.length][];
        for (int i = 0; i < countGrid.length; i++) {
            newCountGrid[i] = new int[countGrid[i].length];
            for (int j = 0; j < countGrid[i].length; j++) {
                newCountGrid[i][j] = countGrid[i][j];
            }
        }
        return newCountGrid;
    }

    public boolean isBombAtLocation(int row, int column) {
        return bombGrid[row][column];
    }

    public int getCountAtLocation(int row, int column) {
        return countGrid[row][column];
    }

    private void createBombGrid() {
        int bombsCount = 0;
        Random random = new Random();

        while (bombsCount < numBombs) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numColumns);
            if (!bombGrid[row][col]) {
                bombGrid[row][col] = true;
                bombsCount++;
            }
        }
    }

    private void createCountGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (bombGrid[i][j]) {
                    countGrid[i][j] = -1; // -1 indicates a bomb
                } else {
                    countGrid[i][j] = countAdjacentBombs(i, j);
                }
            }
        }
    }

    private int countAdjacentBombs(int row, int col) {
    	int count = 0;
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {     
                if (i == 0 && j == 0) continue; 
                int newRow = row + i;
                int newCol = col + j; 
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numColumns) {
                    if (bombGrid[newRow][newCol]) {
                        count++; 
                    }
                }
            }
        }
        return count;
    }

    private void populateBoard() {
        board = new JPanel(new GridLayout(numRows, numColumns));
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonClickListener(i, j));
                board.add(button);
            }
        }
        add(board);
    }

    public void askToContinue() {
        int result = JOptionPane.showConfirmDialog(null, "Would you like to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
        	resetGame();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    private void resetGame() {
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        clearAllButtons();
        initializeGame();
        
    }
    
    private void revealAllCells() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                JButton button = (JButton) board.getComponent(i * numColumns + j);
                if (bombGrid[i][j]) {
                    button.setText("BOMB!");
                } else {
                    button.setText(String.valueOf(countGrid[i][j]));
                }
                button.setEnabled(false); // Disable the button after revealing
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (bombGrid[row][col]) {
                button.setText("BOMB!");
                revealAllCells();
                JOptionPane.showMessageDialog(null, "Game over, you lost", "Information", JOptionPane.INFORMATION_MESSAGE);
                askToContinue();
                
            } else {
                button.setText(String.valueOf(countGrid[row][col]));
                button.setEnabled(false);
                if (checkWinCondition()) {
                    JOptionPane.showMessageDialog(null, "You won!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    askToContinue();
                    clearAllButtons();
                }
            }
        }
    }

    private boolean checkWinCondition() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (!bombGrid[i][j] && countGrid[i][j] != -1) {
                    return false; // There are still non-bomb cells to uncover
                }
            }
        }
        return true; // All non-bomb cells have been uncovered
    }

    private void clearAllButtons() {
        for (Component c : board.getComponents()) {
            JButton button = (JButton) c;
            button.setText("");
            button.setEnabled(true);
        }
    }

    
    public static void main(String[] args) {
        grid1 g = new grid1();
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setSize(400, 400);
        g.setVisible(true);
        
    }  
    

}


