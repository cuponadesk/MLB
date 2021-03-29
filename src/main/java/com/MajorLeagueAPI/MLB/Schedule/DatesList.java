package com.MajorLeagueAPI.MLB.Schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatesList {

    Date date;
    Integer totalItems;
    Integer totalEvents;
    Integer totalGames;
    Integer totalGamesInProgress;
    List<Game> games = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

}
