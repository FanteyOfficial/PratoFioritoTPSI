package GameView;

import java.util.Random;

public class Game {
    char[][] table;
    char[][] tableHidden;
    int rows;
    int columns;

    public Game() {
        this.rows = 10;
        this.columns = 10;
        this.table = new char[rows][columns];
        this.tableHidden = new char[rows][columns];
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

        // test tablehidden
        g.hideTable();
        g.revealTable(0, 0);
        //g.printTableHidden();
        System.out.println(g.getTableHiddenString());
    }

    public boolean isWinner() {
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.columns; j++) {
                if (this.table[i][j] != '@' && this.tableHidden[i][j] != this.table[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void revealTable() {
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.columns; j++) {
                this.tableHidden[i][j] = this.table[i][j];
            }
        }
    }

    public void hideTable() {
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.columns; j++) {
                this.tableHidden[i][j] = 'X';
            }
        }
    }

    public char[][] getTableHidden() {
        return this.tableHidden;
    }

    public void revealTable(int x, int y) {
        this.tableHidden[x][y] = this.table[x][y];
    }

    public void printTableHidden() {
        for (int i = 0; i < tableHidden.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < tableHidden[i].length; j++) { //this equals to the column in each row.
                System.out.print(tableHidden[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    public String getTableHiddenString() {
        String s = "";
        for (int i = 0; i < tableHidden.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < tableHidden[i].length; j++) { //this equals to the column in each row.
                s += tableHidden[i][j] + " ";
            }
            s += "\n"; //change line on console as row comes to end in the matrix.
        }
        return s;
    }
}
