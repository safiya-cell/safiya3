//https://www.youtube.com/watch?v=L5sX5xdfv6s
//import java.awt.Component;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.Random;

//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;


public class Grid /*extends JFrame*/ { 
    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;
    
    
    
    
    public Grid() {
    	numRows = 10;
    	numColumns = 10;
    	bombGrid = new boolean[numRows][numColumns];
    	countGrid = new int[numRows][numColumns];
    	numBombs = 25;
    	createBombGrid();
    	createCountGrid();
    	//initializeGame();

    }
    
    public Grid(int rows, int columns) {
    	this.numRows = rows;
    	this.numColumns = columns;
    	bombGrid = new boolean[numRows][numColumns];
    	countGrid = new int[numRows][numColumns];
    	numBombs = 25;
    	createBombGrid();
    	createCountGrid();
    }
    
    public Grid(int rows, int columns, int bombs) {
    	this.numRows =rows;
    	this.numColumns = columns;
    	this.numBombs = bombs;
    	bombGrid = new boolean[numRows][numColumns];
    	countGrid = new int[numRows][numColumns];
    	createBombGrid();
    	createCountGrid();
    }
    
//    private void initializeGame() {
//        createBombGrid();
//        createCountGrid();
//        populateBoard();
//    }

    
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
        boolean[][] newBombGrid = new boolean[bombGrid.length][bombGrid[0].length];
        for (int i = 0; i < bombGrid.length; i++) {
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
    	if (row >= 0 && row < numRows && column >= 0 && column < numColumns) {
			return bombGrid[row][column];
		}
		return false;
	
    }
    
    public int getCountAtLocation(int row, int column) {
        return countGrid[row][column];
    }
    
    private void createBombGrid() {
		Random random = new Random();
		for (int i = 0; i < numBombs; i++) {
			int row, column;
			row = random.nextInt(numRows);
			column = random.nextInt(numColumns);
			while (bombGrid[row][column] == true) {
				row = random.nextInt(numRows);
				column = random.nextInt(numColumns);
			}
			bombGrid[row][column] = true;
		}
	}
    
    private void createCountGrid() {
		int count = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (bombGrid[i][j] == true) {
					count++;
				}
				if (i > 0 && bombGrid[i - 1][j] == true) {
					count++;
				}
				if (j > 0 && bombGrid[i][j - 1] == true) {
					count++;
				}
				if (i < numRows - 1 && bombGrid[i + 1][j] == true) {
					count++;
				}
				if (j < numColumns - 1 && bombGrid[i][j + 1] == true) {
					count++;
				}
				if (i > 0 && j > 0 && bombGrid[i - 1][j - 1] == true) {
					count++;
				}
				if (i < numRows - 1 && j > 0 && bombGrid[i + 1][j - 1] == true) {
					count++;
				}
				if (i > 0 && j < numColumns - 1 && bombGrid[i - 1][j + 1] == true) {
					count++;
				}
				if (i < numRows - 1 && j < numColumns - 1 && bombGrid[i + 1][j + 1] == true) {
					count++;
				}
				countGrid[i][j] = count;
				count = 0;
			}
		}
	}

//    private void populateBoard() {
//        board = new JPanel(new GridLayout(numRows, numColumns));
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                JButton button = new JButton();
//                button.addActionListener(new ButtonClickListener(i, j));
//                board.add(button);
//            }
//        }
//        add(board);
//    }
//
//    public void YesorNo() {
//        int result = JOptionPane.showConfirmDialog(null, "Would you like to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);
//        if (result == JOptionPane.YES_OPTION) {
//            resetGame();
//        } else {
//        	resetGame();
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        }
//    }
//
//    private void resetGame() {
//        bombGrid = new boolean[numRows][numColumns];
//        countGrid = new int[numRows][numColumns];
//        clearAllButtons();
//        initializeGame();
//        
//    }
//    
//    private void revealAllCells() {
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                JButton button = (JButton) board.getComponent(i * numColumns + j);
//                if (bombGrid[i][j]) {
//                    button.setText("BOMB!");
//                } else {
//                    button.setText(String.valueOf(countGrid[i][j]));
//                }
//                button.setEnabled(false); 
//            }
//        }
//    }
//
//    private class ButtonClickListener implements ActionListener {
//        private int row;
//        private int col;
//
//        public ButtonClickListener(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JButton button = (JButton) e.getSource();
//            if (bombGrid[row][col]) {
//                button.setText("BOMB!");
//                revealAllCells();
//                JOptionPane.showMessageDialog(null, "Game over, you lost", "Information", JOptionPane.INFORMATION_MESSAGE);
//                YesorNo();
//                
//            } else {
//                button.setText(String.valueOf(countGrid[row][col]));
//                button.setEnabled(false);
//                if (checkWinCondition()) {
//                    JOptionPane.showMessageDialog(null, "You won!", "Information", JOptionPane.INFORMATION_MESSAGE);
//                    YesorNo();
//                    clearAllButtons();
//                }
//            }
//        }
//    }
//
//    private boolean checkWinCondition() {
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                if (!bombGrid[i][j] && countGrid[i][j] != -1) {
//                    return false; 
//                }
//            }
//        }
//        return true; 
//    }
//
//    private void clearAllButtons() {
//        for (Component c : board.getComponents()) {
//            JButton button = (JButton) c;
//            button.setText("");
//            button.setEnabled(true);
//        }
//    }

    
    public static void main(String[] args) {
        Grid g = new Grid();
//        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        g.setSize(400, 400);
//        g.setVisible(true);
        
        for (int i = 0; i < g.countGrid.length; i++) {
            for (int j = 0; j < g.countGrid[i].length; j++) {
                System.out.print(g.countGrid[i][j] + " ");
            }
            System.out.println();
        }
        
        
    }
}

