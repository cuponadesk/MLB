package com.MajorLeagueAPI.MLB.Schedule;


public class Teams extends Object{

    ScheduleTeam home;
    ScheduleTeam away;

    public ScheduleTeam getHome() {
        return home;
    }

    public void setHome(ScheduleTeam home) {
        this.home = home;
    }

    public ScheduleTeam getAway() {
        return away;
    }

    public void setAway(ScheduleTeam away) {
        this.away = away;
    }

}
