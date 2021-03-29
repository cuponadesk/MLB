package com.MajorLeagueAPI.MLB;

import com.MajorLeagueAPI.MLB.Schedule.Schedule;
import com.MajorLeagueAPI.MLB.Team.Teams;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.MajorLeagueAPI.MLB.MLB.API_BASE_URL;
import static com.MajorLeagueAPI.MLB.MLB.USE_TODAY;

public class MLBAPI {






   private static final String API_SCHEDULE_PREFIX = "api/v1/schedule/games?&sportId=1";
   private static final String API_SCHEDULE_START_DATE_PREFIX = "&startDate=";
   private static final String API_SCHEDULE_END_DATE_PREFIX = "&endDate=";
   private static final String API_LINESCORE_PREFIX = "api/v1/game/{gamePk}/linescore";
   private static final String API_TEAMS_PREFIX = "api/v1/teams?sportId=1";
   private static RestTemplate restTemplate = new RestTemplate();




   private Schedule base = new Schedule();
   private String startDate;
   private String endDate;

   private String currentGamePK;
   private Teams teams;



   private boolean started=false;

   /*//////////////////////////////////////////
   START PUBLIC METHODS
   *//////////////////////////////////////////

   /*START - Initialize MLB object and retrieve games happening on a specific date.*/
   public MLBAPI(String exactDate) {
      if(exactDate.equals(USE_TODAY)) {
         startDate = dateObjectToString(new Date());
      }
      else {
         startDate = exactDate;
      }
      endDate = null;
      populateDatesData();
      populateTeamsData();
   }

   public MLBAPI(Date exactDate) {
      startDate = dateObjectToString(exactDate);
      endDate = null;
   }
   /*END - Initialize MLB object and retrieve games happening on a specific date.*/

   /*START - Initialize MLB object and retrieve games happening in a range of dates*/
   public MLBAPI(String startDate, String endDate) {
      this.endDate=endDate;
      this.startDate=startDate;
   }

   public MLBAPI(Date startDate, Date endDate) {
      this.endDate=dateObjectToString(endDate);
      this.startDate=dateObjectToString(startDate);
   }
   /*END - Initialize MLB object and retrieve games happening in a range of dates*/

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public Schedule getBase() {
      return base;
   }


   public String getCurrentGamePK() {
      return currentGamePK;
   }

   public void setCurrentGamePK(String currentGamePK) {
      this.currentGamePK = currentGamePK;
   }

   public void updateSchedule() {
      this.populateDatesData();
   }


   /*//////////////////////////////////////////
   END PUBLIC METHODS
   *//////////////////////////////////////////

   /*//////////////////////////////////////////
   START PRIVATE METHODS
   *//////////////////////////////////////////

   /*Populate MLBBase from provided date(s)*/

   private void populateDatesData() {
      if(endDate != null && startDate != null) {
         base = restTemplate.getForObject(API_BASE_URL + API_SCHEDULE_PREFIX + API_SCHEDULE_START_DATE_PREFIX + startDate + API_SCHEDULE_END_DATE_PREFIX + endDate, Schedule.class);
      }
      else if(startDate != null && endDate == null) {
         base = restTemplate.getForObject(API_BASE_URL + API_SCHEDULE_PREFIX + API_SCHEDULE_START_DATE_PREFIX + startDate + API_SCHEDULE_END_DATE_PREFIX + startDate, Schedule.class);
      }
   }

   private void populateTeamsData() {
      teams = restTemplate.getForObject(API_BASE_URL + API_TEAMS_PREFIX, Teams.class);
   }


   /*//////////////////////////////////////////
   END PRIVATE METHODS
   *//////////////////////////////////////////

   protected String dateObjectToString(Date day) {
      String pattern = "MM/dd/yyyy";
      return new SimpleDateFormat(pattern).format(day);
   }

   public Teams getTeams() {
      return teams;
   }

   public void setTeams(Teams teams) {
      this.teams = teams;
   }

}
