package com.MajorLeagueAPI.MLB.Linescore;

public class Offense {

   Player batter;
   Player onDeck;
   Player inHole;
   Player first;
   Player second;
   Player third;
   Player pitcher;
   int battingOrder;

   public Player getBatter() {
      return batter;
   }

   public void setBatter(Player batter) {
      this.batter = batter;
   }

   public Player getOnDeck() {
      return onDeck;
   }

   public void setOnDeck(Player onDeck) {
      this.onDeck = onDeck;
   }

   public Player getInHole() {
      return inHole;
   }

   public void setInHole(Player inHole) {
      this.inHole = inHole;
   }

   public Player getFirst() {
      return first;
   }

   public void setFirst(Player first) {
      this.first = first;
   }

   public Player getSecond() {
      return second;
   }

   public void setSecond(Player second) {
      this.second = second;
   }

   public Player getThird() {
      return third;
   }

   public void setThird(Player third) {
      this.third = third;
   }

   public Player getPitcher() {
      return pitcher;
   }

   public void setPitcher(Player pitcher) {
      this.pitcher = pitcher;
   }

   public int getBattingOrder() {
      return battingOrder;
   }

   public void setBattingOrder(int battingOrder) {
      this.battingOrder = battingOrder;
   }
}
