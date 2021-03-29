package com.MajorLeagueAPI.MLB.Linescore;

public class Defense {
   Player pitcher;
   Player catcher;
   Player first;
   Player second;
   Player third;
   Player shortstop;
   Player left;
   Player center;
   Player right;
   Player batter;
   Player onDeck;
   Player inHole;
   int battingOrder;
   Team team;

   public Player getPitcher() {
      return pitcher;
   }

   public void setPitcher(Player pitcher) {
      this.pitcher = pitcher;
   }

   public Player getCatcher() {
      return catcher;
   }

   public void setCatcher(Player catcher) {
      this.catcher = catcher;
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

   public Player getShortstop() {
      return shortstop;
   }

   public void setShortstop(Player shortstop) {
      this.shortstop = shortstop;
   }

   public Player getLeft() {
      return left;
   }

   public void setLeft(Player left) {
      this.left = left;
   }

   public Player getCenter() {
      return center;
   }

   public void setCenter(Player center) {
      this.center = center;
   }

   public Player getRight() {
      return right;
   }

   public void setRight(Player right) {
      this.right = right;
   }

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

   public int getBattingOrder() {
      return battingOrder;
   }

   public void setBattingOrder(int battingOrder) {
      this.battingOrder = battingOrder;
   }

   public Team getTeam() {
      return team;
   }

   public void setTeam(Team team) {
      this.team = team;
   }
}
