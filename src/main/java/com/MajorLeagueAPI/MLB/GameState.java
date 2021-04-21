package com.MajorLeagueAPI.MLB;

public class GameState {

   private int balls =0,strikes=0, outs=0;
   private boolean first=false, second=false, third=false;
   private int inning=1;
   private int inningHalf=0;
   private String batting="", pitching="";
   private int homeRuns=0, homeHits=0, homeErrors=0;
   private int awayRuns=0, awayHits=0, awayErrors=0;
   private String homeName="", awayName="";
   private String homeNameShort="", awayNameShort = "";
   private String startTime;
   private String status;
   private boolean updateAble;
   private String inningHalfWord = "";

   public String getInningHalfWord() {
      return inningHalfWord;
   }

   public void setInningHalfWord(String inningHalfWord) {
      this.inningHalfWord = inningHalfWord;
   }

   public boolean isUpdateAble() {
      return updateAble;
   }

   public void setUpdateAble(boolean updateAble) {
      this.updateAble = updateAble;
   }

   public int getBalls() {
      return balls;
   }

   public void setBalls(int balls) {
      this.balls = balls;
   }

   public int getStrikes() {
      return strikes;
   }

   public void setStrikes(int strikes) {
      this.strikes = strikes;
   }

   public int getOuts() {
      return outs;
   }

   public void setOuts(int outs) {
      this.outs = outs;
   }

   public boolean isFirst() {
      return first;
   }

   public void setFirst(boolean first) {
      this.first = first;
   }

   public boolean isSecond() {
      return second;
   }

   public void setSecond(boolean second) {
      this.second = second;
   }

   public boolean isThird() {
      return third;
   }

   public void setThird(boolean third) {
      this.third = third;
   }

   public int getInning() {
      return inning;
   }

   public void setInning(int inning) {
      this.inning = inning;
   }

   public int getInningHalf() {
      return inningHalf;
   }

   public void setInningHalf(int inningHalf) {
      this.inningHalf = inningHalf;
   }

   public String getBatting() {
      return batting;
   }

   public void setBatting(String batting) {
      this.batting = batting;
   }

   public String getPitching() {
      return pitching;
   }

   public void setPitching(String pitching) {
      this.pitching = pitching;
   }

   public int getHomeRuns() {
      return homeRuns;
   }

   public void setHomeRuns(int homeRuns) {
      this.homeRuns = homeRuns;
   }

   public int getHomeHits() {
      return homeHits;
   }

   public void setHomeHits(int homeHits) {
      this.homeHits = homeHits;
   }

   public int getHomeErrors() {
      return homeErrors;
   }

   public void setHomeErrors(int homeErrors) {
      this.homeErrors = homeErrors;
   }

   public int getAwayRuns() {
      return awayRuns;
   }

   public void setAwayRuns(int awayRuns) {
      this.awayRuns = awayRuns;
   }

   public int getAwayHits() {
      return awayHits;
   }

   public void setAwayHits(int awayHits) {
      this.awayHits = awayHits;
   }

   public int getAwayErrors() {
      return awayErrors;
   }

   public void setAwayErrors(int awayErrors) {
      this.awayErrors = awayErrors;
   }

   public String getHomeName() {
      return homeName;
   }

   public void setHomeName(String homeName) {
      this.homeName = homeName;
   }

   public String getAwayName() {
      return awayName;
   }

   public void setAwayName(String awayName) {
      this.awayName = awayName;
   }

   public String getStartTime() {
      return startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getHomeNameShort() {
      return homeNameShort;
   }

   public void setHomeNameShort(String homeNameShort) {
      this.homeNameShort = homeNameShort;
   }

   public String getAwayNameShort() {
      return awayNameShort;
   }

   public void setAwayNameShort(String awayNameShort) {
      this.awayNameShort = awayNameShort;
   }

   public String toString() {
      String output = "";
      output += this.inningHalf + " " + this.inning + '\n';
      output += this.homeName + " " + this.homeRuns + " " + this.homeHits + " " + this.homeErrors + "\n";
      output += this.awayName + " " + this.awayRuns + " " + this.awayHits + " " + this.awayErrors + "\n";
      output += "Pitching: " + this.pitching + " Batting: " + this.batting + "\n";
      output += "Balls: " + this.balls + " Strikes: " + this.strikes + " Outs: " + this.outs + "\n";
      output += "First: " + (this.first?"Occupied":"Empty") + " Second: " + (this.second?"Occupied":"Empty") + " Third: " + (this.third?"Occupied":"Empty");
      return output;
   }
}
