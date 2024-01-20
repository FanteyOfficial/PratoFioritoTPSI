package GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    JLabel winTitle;

    JButton startBtn;
    JButton quitBtn;

    public MainMenu() {
        winTitle = new JLabel("Aim Trainer Menu");
        winTitle.setLocation((400/2)-(300/2)+10, 5);
        winTitle.setSize(300,50);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        startBtn = new JButton("Start Game");
        startBtn.setBorderPainted(false);
        startBtn.setBackground(Color.decode("#BB4430"));
        startBtn.setForeground(Color.decode("#231F20"));
        startBtn.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        startBtn.setFocusPainted(false);
        startBtn.addActionListener(startGame);
        startBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                startBtn.setBackground(Color.decode("#E9D758"));
                startBtn.setForeground(Color.decode("#231F20"));
                startBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                //startButton.setBackground(UIManager.getColor("control"));
                startBtn.setBackground(Color.decode("#BB4430"));
                startBtn.setForeground(Color.decode("#231F20"));
                startBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        startBtn.setSize(200, 50);
        startBtn.setLocation((400/2)-(200/2), 100);

        quitBtn = new JButton("QUIT");
        quitBtn.setBorderPainted(false);
        quitBtn.setBackground(Color.decode("#0CF574"));
        quitBtn.setForeground(Color.decode("#231F20"));
        quitBtn.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        quitBtn.setFocusPainted(false);
        quitBtn.addActionListener(close);
        quitBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                quitBtn.setBackground(Color.decode("#E9D758"));
                quitBtn.setForeground(Color.decode("#231F20"));
                quitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                //startButton.setBackground(UIManager.getColor("control"));
                quitBtn.setBackground(Color.decode("#0CF574"));
                quitBtn.setForeground(Color.decode("#231F20"));
                quitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        quitBtn.setSize(120, 50);
        quitBtn.setLocation((400/2)-(120/2), 200);

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

        add(startBtn);
        add(quitBtn);

        setVisible(true);
    }

    ActionListener startGame = new ActionListener() {
        public void actionPerformed(ActionEvent action) {
            dispose();
            GameWindow w;
            try {
                w = new GameWindow();
                w.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    ActionListener close = new ActionListener() {
        public void actionPerformed(ActionEvent action) {
            setVisible(false);
            dispose();
        }
    };

    public static void main(String[] args) {
        MainMenu m = new MainMenu();
    }
}