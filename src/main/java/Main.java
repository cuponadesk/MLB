import com.MajorLeagueAPI.MLB.MLB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Main extends JFrame {

    JFrame frame = new JFrame();
    Color grassGreen = new Color(0,128,24);
    MLB mlb = new MLB("Cincinnati Reds");


    public Main() {
        mlb.gameToday("Cincinnati Reds");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Panel(mlb));
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}
class Panel extends JPanel {
    Timer timer;
    MLB mlb;
    Panel(MLB mlb) {
        this.mlb = mlb;
        setBackground(new Color(196,196,196));
        setForeground(Color.WHITE);
        refreshScreen();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Long d = new Date().getTime();
        g.setFont(new Font("arial", Font.PLAIN, 24));
        if( mlb.isGameToday() && mlb.isGameStarted()) {
//        System.out.println(mlb.getState().getHomeName());
            g.drawString(mlb.getState().getHomeName(), 6, 24);
            g.drawString(mlb.getState().getAwayName(), 6, 54);
        }
        else if( mlb.isGameToday() && mlb.isGameStarted()) {            //Draw Preview

        }
//        boolean spaceNeeded = false;
//
//        if(mlb.getState().getHomeRuns() > 9 || mlb.getState().getAwayRuns() > 9){
//            spaceNeeded = true;
//        }
//
//        int xOffset;
//
//        g.drawString(Integer.toString(mlb.getState().getHomeRuns()), 120, 24);
//        g.drawString(Integer.toString(mlb.getState().getAwayRuns()), 120, 54);
//
//        if(spaceNeeded) {

//        }
    }
    public void refreshScreen() {
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mlb.tick();
                repaint();
            }
        });
        timer.setRepeats(true);
        // Aprox. 1 FPS
        timer.setDelay(1000);
        timer.start();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(650, 480);
    }
}

//public class Application {
//
//    public static void main(String[] args) {
//        MLB mlb = new MLB();
//        System.out.println(mlb.gameToday("Cincinnati Reds"));
//        while(true) {
//            if(mlb.tick()) {
//                System.out.println(mlb.getState().toString() + '\n');
//            }
//        }




//        String pattern = "MM/dd/yyyy";
//        SimpleDateFormat formatter, FORMATTER;
//        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        String dateInString = new SimpleDateFormat(pattern).format(new Date());
//        MLBBase base = restTemplate.getForObject(BASE_URL+
//              dateInString
////              "04/28/2021"
//              , MLBBase.class);
//        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        FORMATTER = new SimpleDateFormat("hh:mm a");
//
//        int x = 1;
//        if (base.getDates().size() > 0) {
//            try {
//                for (Game g : base.getDates().get(0).getGames()) {
//                    Calendar gameTime = Calendar.getInstance();
//                    gameTime.setTime(formatter.parse(g.getGameDate()));
//                    Date d = gameTime.getTime();
//
//                    Status s = g.getStatus();
//                    ScheduleTeam home = g.getTeams().getHome();
//                    ScheduleTeam away = g.getTeams().getAway();
////                    System.out.println(s.getDetailedState());
//
//                    switch(s.getCodedGameState()) {
//                        //Scheduled
//                        case 'S' :
//                        case 'P' :
////                            System.out.println("Scheduled " + d.);
//                            String lineOne = home.getTeam().getName();
//                            String lineTwo = away.getTeam().getName();
//                            break;
//
//                    }
//                    String spaces = " ";
//
//                        spaces = spaces.repeat(25 - home.getTeam().getName().length() - home.getScore().toString().length());
//                        System.out.println(home.getTeam().getName() + spaces + home.getScore() + (home.getIsWinner() ? " Winner" : " "));
//                        spaces = " ";
//                        spaces = spaces.repeat(25 - away.getTeam().getName().length() - away.getScore().toString().length());
//                        System.out.println(away.getTeam().getName() + spaces + away.getScore() + (away.getIsWinner() ? " Winner" : " "));
//                        System.out.printf("GamePk: %d%n%n", g.getGamePk());
//                }
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
//        }
//        else {
//            System.out.println("No games today");
//        }
//    }
//}