package tokrystkowiak.SnakeGame2;

import javax.swing.*;
import java.awt.*;

public class Ramka extends JFrame{

    private snakePanel sP;
    public Ramka(){
        sP = new snakePanel();
        setContentPane(sP);
        setVisible(true);
        setSize(new Dimension(300,300));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(sP);
        pack();
        repaint();
        sP.snakeThread.run();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
