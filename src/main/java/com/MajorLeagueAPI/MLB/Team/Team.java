package com.MajorLeagueAPI.MLB.Team;

public class Team {
   int id;
   String name;
   String link;
   String teamCode;
   String fileCode;
   String abbreviation;
   String teamName;
   String locationName;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLink() {
      return link;
   }

   public void setLink(String link) {
      this.link = link;
   }

   public String getTeamCode() {
      return teamCode;
   }

   public void setTeamCode(String teamCode) {
      this.teamCode = teamCode;
   }

   public String getFileCode() {
      return fileCode;
   }

   public void setFileCode(String fileCode) {
      this.fileCode = fileCode;
   }

   public String getAbbreviation() {
      return abbreviation;
   }

   public void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation;
   }

   public String getTeamName() {
      return teamName;
   }

   public void setTeamName(String teamName) {
      this.teamName = teamName;
   }

   public String getLocationName() {
      return locationName;
   }

   public void setLocationName(String locationName) {
      this.locationName = locationName;
   }
}
