package com.MajorLeagueAPI.MLB;

import com.MajorLeagueAPI.MLB.Linescore.Linescore;
import com.MajorLeagueAPI.MLB.Schedule.Game;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MLB {

   public static final String API_BASE_URL = "http://statsapi.mlb.com/";
   public static final String USE_TODAY = "USE_TODAYS_DATE_AS_QUERY_PARAM";
   protected static final String API_TIMESTAMPS_PREFIX =  "api/v1.1/game/{gamePk}/feed/live/timestamps";
   public static final String API_LINESCORE_PREFIX = "api/v1/game/{gamePk}/linescore?timecode=";



   private ArrayList<String> timeStamps = new ArrayList<>();
   private static RestTemplate restTemplate = new RestTemplate();
   private long lastTimeStampQuery;
   private boolean newData;
   private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000 * 15;
   private GameState state = new GameState();
   MLBAPI api;
   Linescore linescore;

   private Integer todayGamePk;
   private Game todayGame;
   private String gameStartTimeUTC;
   private Date gameStartTimeAsDate;
   private String compactGameStartTime;

   private boolean gameToday;
   private boolean gameStarted;

   public GameState getState() {
      return state;
   }

   public MLB(String teamName){
      api = new MLBAPI(USE_TODAY);
      gameToday = gameToday(teamName);
      if(gameToday) {
         gameStarted = gameStartTimeAsDate.compareTo(new Date()) < 0;
      }

   }

   public boolean isGameToday() {
      return gameToday;
   }


   public boolean isGameStarted() {
      return gameStarted;
   }

   public MLBAPI getApi() {
      return api;
   }

   public void setApi(MLBAPI api) {
      this.api = api;
   }

   public Linescore getLinescore() {
      return linescore;
   }

   public void setLinescore(Linescore linescore) {
      this.linescore = linescore;
   }

   /**
    *
    * @param teamID int of teamid IE Cincinnati Reds = 113
    * @return True if there is a scheduled game today
    */
   public boolean gameToday(int teamID) {
      return gameToday(api.getTeams().getLocationFromId(teamID) + " " + api.getTeams().getTeamNameFromId(teamID));
   }

   /**
    *
    * @param teamName string of team name, IE Cincinnati Reds
    *                 sets base variables to start tracking the game such as gamePk, game start time.
    * @return True if the passed in team has a game as found from query to api.mlb.com/api/v1/schedule?date=TODAY'S_DATE
    */
   public boolean gameToday(String teamName) {
      List<Game> games = api.getBase().getDates().get(0).getGames();
      for(Game g : games) {
         if(g.getTeams().getAway().getTeam().getName().equals(teamName) || g.getTeams().getHome().getTeam().getName().equals(teamName)) {
            todayGamePk = g.getGamePk();
            todayGame = g;
            setGameStartTimeUTC(g.getGameDate());
            newData = true;
            return true;
         }
      }
      return false;
   }

   public String getGameStartTimeUTC() {
      return gameStartTimeUTC;
   }

   /**
    * Converts time from api game start in format yyyy-MM-dd'T'HH:mm:ss'Z' to human readable form.
    * @param gameStartTimeUTC set game time Date from todayGame time;
    */
   private void setGameStartTimeUTC(String gameStartTimeUTC) {
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      DateFormat simpleFormat = new SimpleDateFormat("h:mm");
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

      try {
         gameStartTimeAsDate = formatter.parse(gameStartTimeUTC);
         this.gameStartTimeUTC = gameStartTimeUTC;
         compactGameStartTime = simpleFormat.format(gameStartTimeAsDate);
      }
      catch (Exception ex) {
         this.gameStartTimeUTC = "0:00 AM";
         System.out.println(ex);
      }
   }

   /**
    * Checks for new timestamp from MLB API for active game
    *
    * @return True if new timestamp
    */
   private boolean checkForNewTimestamp() {

      int oldTimestampSize = timeStamps.size();

      System.out.flush();;
      timeStamps = restTemplate.getForObject(API_BASE_URL + replaceGamePKInString(API_TIMESTAMPS_PREFIX, todayGamePk.toString() ), ArrayList.class);
      try {
         if (timeStamps.size() != oldTimestampSize) {
            return true;
         }
      }
      catch (NullPointerException ex) {
         System.out.println("Null pointer.");
      }
      return false;
   }
   /**
    * Handles checking for new timestamp based on delay set by UPDATE_INTERVAL_IN_MILLISECONDS and checking to see if currentTime is past game start time.
    *
    */
   public boolean tick() {

      long currentTime = new Date().getTime();

      if(todayGame == null ) {
         return false;
      }
      Date comparable = new Date();
//      System.out.println(API_BASE_URL + replaceGamePKInString(API_TIMESTAMPS_PREFIX, todayGamePk.toString()));

      if( comparable.compareTo(gameStartTimeAsDate) > 0 && lastTimeStampQuery + UPDATE_INTERVAL_IN_MILLISECONDS < currentTime) {
         lastTimeStampQuery = currentTime;
         int x = 0;
         if( checkForNewTimestamp() ) {
            newData = true;
            System.out.println("New Timestamp!");
            System.out.println(timeStamps.get(timeStamps.size()-1));
            getNewLinescoreData(timeStamps.get(timeStamps.size()-1), todayGamePk.toString());
            updateGameState();
         }
      }
      else if( comparable.compareTo(gameStartTimeAsDate) < 0 && lastTimeStampQuery + UPDATE_INTERVAL_IN_MILLISECONDS < currentTime) {
         lastTimeStampQuery = currentTime;
         System.out.println("Game not started. Game starts at " + compactGameStartTime + " Gamepk: " + todayGamePk);
         newData = false;
         state.setStatus("Preview");
         state.setStartTime(compactGameStartTime);
      }
      else {
         newData = false;
      }
      return newData;
   }

   /**
    *
    * @param timestamp
    * @param gamePk
    * @return
    */
   private void getNewLinescoreData(String timestamp, String gamePk) {
      linescore = restTemplate.getForObject(API_BASE_URL+replaceGamePKInString(API_LINESCORE_PREFIX, gamePk ) + timestamp, Linescore.class);
   }

   /**
    *
    * @param urlString url to search for and replace {gamePk}
    * @param gamePK int as a string indicating the game number
    * @return updated URL with {gamePk} replaced
    */
   private String replaceGamePKInString(String urlString, String gamePK){
      return urlString.replace("{gamePk}", gamePK);
   }

   public GameState updateGameState() {
//      getNewLinescoreData("20180429_172337", "529820" );

      state.setInning(linescore.getCurrentInning());
      switch (linescore.getInningState()) {
         case "Top" :
            state.setInningHalf(0);
            break;
         case "Middle" :
            state.setInningHalf(1);
            break;
         case "Bottom" :
            state.setInningHalf(2);
            break;
         case "End" :
         default:
            state.setInningHalf(3);
            break;
      }

      if( state.getInningHalf() % 2 == 0) {
         state.setBalls(linescore.getBalls());
         state.setStrikes(linescore.getStrikes());
         state.setOuts(linescore.getOuts());

         state.setFirst(linescore.getOffense().getFirst()!=null);
         state.setSecond(linescore.getOffense().getSecond()!=null);
         state.setThird(linescore.getOffense().getThird()!=null);
      }
      else {
         state.setBalls(0);
         state.setStrikes(0);
         state.setOuts(0);

         state.setFirst(false);
         state.setSecond(false);
         state.setThird(false);
      }

      state.setBatting(linescore.getOffense().getBatter().getFullName());
      state.setPitching(linescore.getDefense().getPitcher().getFullName());

      state.setHomeErrors(linescore.getTeams().getHome().getErrors());
      state.setHomeHits(linescore.getTeams().getHome().getHits());
      state.setHomeRuns(linescore.getTeams().getHome().getRuns());

      state.setAwayErrors(linescore.getTeams().getAway().getErrors());
      state.setAwayHits(linescore.getTeams().getAway().getHits());
      state.setAwayRuns(linescore.getTeams().getAway().getRuns());

      state.setHomeName( api.getTeams().getTeamNameFromId( todayGame.getTeams().getHome().getTeam().getId() ) );
      state.setAwayName( api.getTeams().getTeamNameFromId( todayGame.getTeams().getAway().getTeam().getId() ) );

      return state;
   }


}
