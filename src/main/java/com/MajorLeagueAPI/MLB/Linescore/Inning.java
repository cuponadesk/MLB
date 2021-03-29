package com.MajorLeagueAPI.MLB.Linescore;

public class Inning {

   int num;
   String ordinalNum;
   Team home;
   Team away;

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public String getOrdinalNum() {
      return ordinalNum;
   }

   public void setOrdinalNum(String ordinalNum) {
      this.ordinalNum = ordinalNum;
   }

   public Team getHome() {
      return home;
   }

   public void setHome(Team home) {
      this.home = home;
   }

   public Team getAway() {
      return away;
   }

   public void setAway(Team away) {
      this.away = away;
   }
}
