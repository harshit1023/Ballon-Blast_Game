package Harshit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

public class Demo implements MouseListener {


    static int ballon1Xcord=50;
    static int ballon1Ycord=900;

    static int ballon2Xcord=200;
    static int ballon2Ycord=1000;

    static int ballon3Xcord=350;
    static int ballon3Ycord=1100;



    static int bomb1Xcord=50;
    static int bomb1Ycord=900;

    static int bomb2Xcord=200;
    static int bomb2Ycord=1200;

    static int bomb3Xcord=350;
    static int bomb3Ycord=1500;

    static boolean ballon1Visible=true;
    static boolean ballon2Visible=true;
    static boolean ballon3Visible=true;
    static int count=0;
    static boolean gameOver=false;
    static boolean pause=false;

    static AudioClip playAudio;
    static AudioClip hitAudio;


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        JPanel panel =new JPanel();
        frame.setLocation(600,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Balloon Booom!");

        frame.add(panel);
        Dimension d= new Dimension(500,700);
        panel.setPreferredSize(d);
        panel.setFocusable(true);

        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        Graphics g=panel.getGraphics();
        panel.addMouseListener(new Demo());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        playAudio= Applet.newAudioClip(Demo.class.getClassLoader().getResource("Harshit/sound/onjump.wav"));
        hitAudio= Applet.newAudioClip(Demo.class.getClassLoader().getResource("Harshit/sound/hit.wav"));



        Image balloon[]=new Image[3];
        Image bomb=null;
        Image play=null,pause=null;


        try {
            balloon[0]= ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/balloon.png"));
            balloon[1]= ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/balloon.png"));
            balloon[2]= ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/balloon.png"));

            bomb   = ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/bomb.png"));
            play = ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/play.png"));
            pause = ImageIO.read(Demo.class.getClassLoader().getResourceAsStream("Harshit/images/pause.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true)
        {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //playAudio.loop();
            if(gameOver)
            {
                g.setColor(Color.green);
                g.fillRect(0,0,500,700);
                g.setColor(Color.black);
                g.setFont(new Font("Arial",Font.BOLD,75));
                g.drawString("Game Over",30,300);
                break;
            }


            Random r=new Random();
            int a=r.nextInt(3);
            //System.out.println(a);
            if(Demo.pause) {
                g.drawImage(play,10,10,50,50,null);

                continue;
            }
                bomb1Ycord -= 5;
                if (bomb1Ycord <= -100) {
                    if (a == 0) {
                        bomb1Xcord = 200;
                    }
                    if (a == 1) {
                        bomb1Xcord = 50;
                    }
                    if (a == 2) {
                        bomb1Xcord = 350;
                    }
                    bomb1Ycord = 900;
                }


                bomb2Ycord -= 5;
                if (bomb2Ycord <= -100) {
                    if (a == 0) {
                        bomb2Xcord = 350;
                    }
                    if (a == 1) {
                        bomb2Xcord = 200;
                    }
                    if (a == 2) {
                        bomb2Xcord = 50;
                    }
                    bomb2Ycord = 900;
                }


                bomb3Ycord -= 5;
                if (bomb3Ycord <= -100) {
                    if (a == 0) {
                        bomb3Xcord = 50;
                    }
                    if (a == 1) {
                        bomb3Ycord = 350;
                    }
                    if (a == 2) {
                        bomb3Ycord = 200;
                    }
                    bomb3Ycord = 900;
                }

                ballon1Ycord -= 8;
                if (ballon1Ycord <= -150) {
                    ballon1Ycord = 900;
                    ballon1Visible = true;
                }

                ballon2Ycord -= 7;
                if (ballon2Ycord <= -150) {
                    ballon2Ycord = 900;
                    ballon2Visible = true;
                }

                ballon3Ycord -= 5;
                if (ballon3Ycord <= -150) {
                    ballon3Ycord = 900;
                    ballon3Visible = true;
                }
                Color clr =new Color(92, 237, 30);
            g.setColor(clr);
            g.fillRect(0,0,500,700);
            if(!Demo.pause)
                g.drawImage(pause,10,10,50,50,null);


            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString(String.valueOf(Demo.count),400,40);

                if (ballon1Visible)
                    g.drawImage(balloon[0], ballon1Xcord, ballon1Ycord, null);
                if (ballon2Visible)
                    g.drawImage(balloon[0], ballon2Xcord, ballon2Ycord, null);
                if (ballon3Visible)
                    g.drawImage(balloon[0], ballon3Xcord, ballon3Ycord, null);

                g.drawImage(bomb, bomb1Xcord, bomb1Ycord, null);
                g.drawImage(bomb, bomb2Xcord, bomb2Ycord, null);
                g.drawImage(bomb, bomb3Xcord, bomb3Ycord, null);


        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getX()>50 && e.getX()<126 && e.getY()>Demo.ballon1Ycord && (e.getY()<Demo.ballon1Ycord+150))
        {
            Demo.ballon1Visible=false;
            Demo.count++;
            playAudio.play();
        }
        if(e.getX()>200 && e.getX()<276 && e.getY()>Demo.ballon2Ycord && (e.getY()<Demo.ballon2Ycord+150))
        {
            Demo.ballon2Visible=false;
            Demo.count++;
            playAudio.play();
        }
        if(e.getX()>350 && e.getX()<426 && e.getY()>Demo.ballon3Ycord && (e.getY()<Demo.ballon3Ycord+150))
        {
            Demo.ballon3Visible=false;
            Demo.count++;
            playAudio.play();
        }
        if(e.getX()>bomb1Xcord && (e.getX()<bomb1Xcord+115) && e.getY()>bomb1Ycord && (e.getY()<bomb1Ycord+100))
        {
            Demo.gameOver=true;
            hitAudio.play();
        }
        if(e.getX()>bomb2Xcord && (e.getX()<bomb2Xcord+115) && e.getY()>bomb2Ycord && (e.getY()<bomb2Ycord+100))
        {
            Demo.gameOver=true;
            hitAudio.play();
        }
        if(e.getX()>bomb3Xcord && (e.getX()<bomb3Xcord+115) && e.getY()>bomb3Ycord && (e.getY()<bomb3Ycord+100))
        {
            Demo.gameOver=true;
            hitAudio.play();
        }
        if (e.getX()>10 && e.getX()<60 && e.getY()>10 && e.getY()<60)
        {
            Demo.pause=!Demo.pause;
            hitAudio.play();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
