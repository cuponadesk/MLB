package com.MajorLeagueAPI.MLB.Linescore;

import java.util.List;

public class Linescore {
  private String copyright;
  private int currentInning;
  private String currentInningOrdinal;
  private String inningState;
  private String inningHalf;
  boolean isTopInning;
  int scheduledInnings;
  List<Inning> innings;
  Teams teams;
  Defense defense;
  Offense offense;
  int balls;
  int strikes;
  int outs;


  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getCurrentInningOrdinal() {
    return currentInningOrdinal;
  }

  public void setCurrentInningOrdinal(String currentInningOrdinal) {
    this.currentInningOrdinal = currentInningOrdinal;
  }

  public int getCurrentInning() {
    return currentInning;
  }

  public void setCurrentInning(int currentInning) {
    this.currentInning = currentInning;
  }

  public String getInningState() {
    return inningState;
  }

  public void setInningState(String inningState) {
    this.inningState = inningState;
  }

  public String getInningHalf() {
    return inningHalf;
  }

  public void setInningHalf(String inningHalf) {
    this.inningHalf = inningHalf;
  }

  public boolean isTopInning() {
    return isTopInning;
  }

  public void setTopInning(boolean topInning) {
    isTopInning = topInning;
  }

  public int getScheduledInnings() {
    return scheduledInnings;
  }

  public void setScheduledInnings(int scheduledInnings) {
    this.scheduledInnings = scheduledInnings;
  }

  public List<Inning> getInnings() {
    return innings;
  }

  public void setInnings(List<Inning> innings) {
    this.innings = innings;
  }

  public Teams getTeams() {
    return teams;
  }

  public void setTeams(Teams teams) {
    this.teams = teams;
  }

  public Defense getDefense() {
    return defense;
  }

  public void setDefense(Defense defense) {
    this.defense = defense;
  }

  public Offense getOffense() {
    return offense;
  }

  public void setOffense(Offense offense) {
    this.offense = offense;
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
}