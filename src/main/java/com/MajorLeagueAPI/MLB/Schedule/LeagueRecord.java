package com.MajorLeagueAPI.MLB.Schedule;

public class LeagueRecord extends Object{

    Integer wins;
    Integer losses;
    Float pct;

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Float getPct() {
        return pct;
    }

    public void setPct(Float pct) {
        this.pct = pct;
    }

}
