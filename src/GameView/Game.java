package GameView;

import java.util.Random;

public class Game {
    char[][] table;
    int rows;
    int columns;

    public Game() {
        this.rows = 10;
        this.columns = 10;
        this.table = new char[rows][columns];
    }

    public void fillTable() {
        Random r = new Random();

        int nMines = (rows*columns) / 2;

        for (int i=0;i<nMines; i++) {
            table[r.nextInt(this.rows)][r.nextInt(this.columns)] = '@';
        }

        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.columns; j++) {
                if (!checkMine(i, j)) {
                    int n = checkBounds(i, j);
                    this.table[i][j] = Character.forDigit(n , 10);
                }
            }
        }

        this.printTable();
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < table[i].length; j++) { //this equals to the column in each row.
                System.out.print(table[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    private int checkBounds(int x, int y) {
        int count = 0;
        for (int i=Math.max(x-1, 0); i<Math.min(x+2, this.rows); i++) {
            for (int j=Math.max(y-1, 0); j<Math.min(y+2, this.columns); j++) {
                if (checkMine(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean checkMine(int x, int y) {
        return table[x][y] == '@';
    }

    public char[][] getTable() {
        return table;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.fillTable();
    }
}
