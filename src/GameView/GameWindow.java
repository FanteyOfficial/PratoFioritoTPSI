package GameView;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    JLabel winTitle;

    Game game;

    JButton[][] table;

    public GameWindow() {
        this.game = new Game();
        this.game.fillTable();

        this.table = generateMineTable(this.game.getTable());

        winTitle = new JLabel("Campo Minato");
        winTitle.setLocation((400/2)-(300/2)+10, 5);
        winTitle.setSize(300,50);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        init();
    }

    private void init() {
        setLayout(null);
        setSize(550, 650);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#3E92CC"));
        setTitle("AimTrainerMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(winTitle);

        placeButtons(this.table);

        setVisible(true);
    }

    private JButton[][] generateMineTable(char mineArray[][]) {
        JButton[][] btnArray = new JButton[mineArray.length][mineArray[0].length];

        for (int i=0; i<mineArray.length; i++) {
            for (int j=0; j<mineArray[i].length; j++) {
                // generate button for every cell
                JButton btn = new JButton();
                btn.setSize(50, 50);
                btn.setLocation(50*j, 50*i+50);
                btn.setBackground(Color.decode("#BB4430"));
                btn.setForeground(Color.BLACK);
                btn.setFont(new Font("Sans Serif", Font.PLAIN, 16));
                btn.setFocusPainted(false);
                btn.setName(i + "," + j);
                btn.addActionListener(e -> btnClicked(btn));
                btnArray[i][j] = btn;
            }
        }

        return btnArray;
    }

    // place buttons on the frame
    private void placeButtons(JButton[][] btn) {
        for (int i=0; i<btn.length; i++) {
            for (int j=0; j<btn[i].length; j++) {
                add(btn[i][j]);
            }
        }
    }

    private void btnClicked(JButton btn) {
        String[] coords = btn.getName().split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);

        this.table[x][y].setText(this.game.getTable()[x][y] + "");
        this.table[x][y].setEnabled(false);

        if (this.game.checkMine(x, y)) {
            System.out.println("Hai perso!");
        } else {
            System.out.println("Hai vinto!");
        }

        System.out.println(x + " " + y);
    }
}
