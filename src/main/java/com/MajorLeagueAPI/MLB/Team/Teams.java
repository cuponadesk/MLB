package com.MajorLeagueAPI.MLB.Team;

import java.util.List;

public class Teams {

   List<Team> teams;

   public List<Team> getTeams() {
      return teams;
   }

   public void setTeams(List<Team> teams) {
      this.teams = teams;
   }

   public String getLocationFromId(int id) {
      if(teams!=null) {
         for(Team t : teams) {
            if(t.id == id) {
               return t.getLocationName();
            }
         }
      }
      return "";
   }

   public String getTeamNameFromId(int id) {
      if(teams!=null) {
         for(Team t : teams) {
            if(t.id == id) {
               return t.getTeamName();
            }
         }
      }
      return "";
   }

   public String getFullTeamNameFromId(int id) {
      if(teams!=null) {
         for(Team t : teams) {
            if(t.id == id) {
               return t.getName();
            }
         }
      }
      return "";
   }
}
