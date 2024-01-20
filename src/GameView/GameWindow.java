package GameView;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    JLabel winTitle;

    public GameWindow() {
        winTitle = new JLabel("Campo Minato");
        winTitle.setLocation((400/2)-(300/2)+10, 5);
        winTitle.setSize(300,50);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        init();
    }

    private void init() {
        setLayout(null);
        setSize(400, 350);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#3E92CC"));
        setTitle("AimTrainerMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(winTitle);

        setVisible(true);
    }
}
