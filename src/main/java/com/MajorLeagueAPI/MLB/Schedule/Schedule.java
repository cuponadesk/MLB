package com.MajorLeagueAPI.MLB.Schedule;

import java.util.*;


public class Schedule {



    String copyright;
    Integer totalItems;
    Integer totalEvents;
    Integer totalGames;
    Integer totalGamesInProgress;
    List<DatesList> dates = new ArrayList<>();

    public Schedule() {
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(Integer totalEvents) {
        this.totalEvents = totalEvents;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public Integer getTotalGamesInProgress() {
        return totalGamesInProgress;
    }

    public void setTotalGamesInProgress(Integer totalGamesInProgress) {
        this.totalGamesInProgress = totalGamesInProgress;
    }

    public List<DatesList> getDates() {
        return dates;
    }

    public void setDates(List<DatesList> dates) {
        this.dates = dates;
    }

}
