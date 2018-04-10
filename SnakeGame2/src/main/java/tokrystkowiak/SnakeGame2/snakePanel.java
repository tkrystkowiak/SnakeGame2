package tokrystkowiak.SnakeGame2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class snakePanel extends JPanel implements KeyListener {
    private int direction;//direcion:0=up,1=down,2=left,3=right.
    private int coX;
    private int coY;
    private int randX;
    private int randY;
    private int segCount;
    private ArrayList<Integer> coXList;
    private ArrayList<Integer> coYList;
    private Segment s1;
    private ArrayList<Segment> segList;
    private Apple ap;
    private Color cSnake;
    private  Random rand;
    public snakePanel(){
        segCount=0;
        coXList = new ArrayList();
        coYList = new ArrayList();
        cSnake = new Color(24,87,56);
        segList = new ArrayList<Segment>();
        direction = 0;
        rand = new Random();
        randX = rand.nextInt(31)*10;
        randY = rand.nextInt(31)*10;
        coX = 150;
        coY = 150;
        Dimension d1 =new Dimension(300,300);
        setPreferredSize(d1);
        addKeyListener(this);
    }
    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        ap = new Apple(randX,randY,10,10,new Color(151,0,9));
        ap.paint(g2D);
        s1 =  new Segment(coX,coY,10,10,cSnake);

        try{
            if(segCount>0){
                for(int i=0; i<segCount;i++) {
                    new Segment(coXList.get(i), coYList.get(i), 10, 10, cSnake).paint(g2D);
                }
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.append("Błąd");
        }
        s1.paint(g2D);
    }

    public Runnable snakeThread = () -> {

        while(true) {

            for(int i=0;i<segCount;i++){

                if(coXList.get(i)==coX&&coYList.get(i)==coY){

                    gameOverHandling();

                }
            }

            if(coX<0||coX>300||coY<0||coY>300){

                gameOverHandling();

            }

            coXList.add(0,coX);
            coYList.add(0,coY);

            if(coXList.size() > 100) {

                coXList.remove(coXList.size() - 1);
                coYList.remove(coYList.size() - 1);

            }

            if(coX==randX&coY==randY){

                randX = rand.nextInt(31)*10;
                randY = rand.nextInt(31)*10;

                segCount++;

            }

            if (direction == 0) {

                coY = coY - 10;

            }

            else if(direction == 1){

                coY = coY + 10;

            }

            else if(direction == 2){

                coX = coX - 10;

            }

            else if(direction == 3){

                coX = coX + 10;

            }

            repaint();

            try {

                sleep(100);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }
    };

    private void gameOverHandling(){
        JLabel label = new JLabel("YOU DIED");
        label.setFont(new Font("Arial", Font.BOLD, 60));
        Object[] options = {"Start Again",
                "Finish Game"};
        int  selected = JOptionPane.showOptionDialog(this, label," ",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(selected==JOptionPane.NO_OPTION){
            System.exit(0);
        }
        if(selected==JOptionPane.YES_OPTION){
            segCount=0;
            coXList.clear();
            coYList.clear();
            coX = 150;
            coY = 150;
            direction = 0;
            repaint();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

         if(e.getKeyCode()==KeyEvent.VK_UP) {
             if(direction!=1)
             direction = 0;
         }

         else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
             if(direction!=0)
             direction = 1;
         }

         else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
             if(direction!=3)
             direction = 2;
         }

         else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
             if(direction!=2)
             direction = 3;
         }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
