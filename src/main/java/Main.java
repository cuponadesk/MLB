import com.MajorLeagueAPI.MLB.GameState;
import com.MajorLeagueAPI.MLB.MLB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Main extends JFrame{

    MLB mlb = new MLB("Cincinnati Reds");
    Timer timer;
    BufferedImage image = null;
    GameState gameData;
    int timerDelay = 1000*10;
    JFrame jp;

    Main()
    {
        jp=new JFrame("spot");
        loadBackgroundImage();
        jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameData = mlb.updateGameState();
        timer = new Timer(0, e -> {
            if(mlb.isGameToday() && mlb.isGameStarted() && !mlb.isTimerStarted()) {
                mlb.startUpdateTimer(10 * 1000);
            }
            else if((!mlb.isGameToday() || !mlb.isGameStarted()) && mlb.isTimerStarted()) {
                mlb.stopUpdateTimer();
            }
            repaint();
        });
        timer.setRepeats(true);
        timer.setDelay(timerDelay);
        timer.start();

    }

    public void paint(Graphics g) {
//        super.paint(g);
        g.setColor(Color.RED);
        g.drawImage(image, 0, 30, null);
        if (gameData.isFirst()) {
            g.fillOval(660, 120, 20, 20);//first
        }
        if (gameData.isThird()) {
            g.fillOval(526, 120, 20, 20);//third
        }
        if (gameData.isSecond()) {
            g.fillOval(593, 50, 20, 20);//second
        }
        g.setColor(Color.BLACK);

        g.setFont(new Font("arial", Font.BOLD, 24));
        g.drawString(gameData.getHomeName(), 50, 100);
        g.drawString(Integer.toString(gameData.getHomeRuns()), 350, 100);
        g.drawString(Integer.toString(gameData.getHomeHits()), 400, 100);
        g.drawString(Integer.toString(gameData.getHomeErrors()), 450, 100);

        g.drawString(gameData.getAwayName(), 50, 140);

        g.drawString(Integer.toString(gameData.getAwayRuns()), 350, 140);
        g.drawString(Integer.toString(gameData.getAwayHits()), 400, 140);
        g.drawString(Integer.toString(gameData.getAwayErrors()), 450, 140);

        g.drawString(gameData.getInningHalfWord() + " " + gameData.getInning(), 50, 64);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Pitching: " + gameData.getPitching(), 50, 190);
        g.drawString("Batting: " + gameData.getBatting(), 50, 215);


        g.drawString("Balls", 350, 190);
        g.drawString("Strikes", 350, 215);
        g.drawString("Outs", 460, 190);

        Color deepBlue = new Color(0, 27, 164);
        g.setColor(deepBlue);
        int xStart = 393;
        for (int i = 0; i < gameData.getBalls(); i++) {
            g.fillOval(xStart + i * 15, 179, 10, 10);
        }
        g.setColor(new Color(128, 0, 0));
        xStart = 406;
        for (int i = 0; i < gameData.getStrikes(); i++) {
            g.fillOval(xStart + i * 15, 205, 10, 10);
        }
        g.setColor(Color.BLACK);
        xStart = 500;
        for (int i = 0; i < gameData.getOuts(); i++) {
            g.fillOval(xStart + i * 15, 179, 10, 10);
        }


        int yBatting = 80;

        if (gameData.getInningHalf() < 1) {
            yBatting += 40;
        }
        g.setColor(Color.white);
        g.fillOval(20, yBatting, 20, 20);
    }

    public static void main(String...args)
    {

        Main obj = new Main();
        obj.setSize(710,240);
        obj.setVisible(true);

    }

    private void loadBackgroundImage() {
        String fileName = "3.jpg";
        ClassLoader classLoader = getClass().getClassLoader();
        try{
            File file = new File(classLoader.getResource(fileName).getFile());
            image =  ImageIO.read(file);
        }
        catch (Exception e) {
            System.out.println( e);
        }
    }

    private void sizeBugPatch() {
        while (jp.getWidth() > 710) {
            jp.pack();
        }
    }
}
