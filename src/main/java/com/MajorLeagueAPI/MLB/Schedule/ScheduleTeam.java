package com.MajorLeagueAPI.MLB.Schedule;

public class ScheduleTeam {

        LeagueRecord leagueRecord;
        Integer score;
        TeamInfo team;
        boolean isWinner;
        Boolean splitSquad;
        Integer seriesNumber;

        public Integer getScore() {
                return score;
        }

        public void setScore(Integer score) {
                this.score = score;
        }

        public TeamInfo getTeam() {
                return team;
        }

        public void setTeam(TeamInfo team) {
                this.team = team;
        }

        public Boolean getIsWinner() {
                return isWinner;
        }

        public void setIsWinner(Boolean winner) {
                isWinner = winner;
        }

        public boolean getSplitSquad() {
                return splitSquad;
        }

        public void setSplitSquad(boolean splitSquad) {
                this.splitSquad = splitSquad;
        }

        public Integer getSeriesNumber() {
                return seriesNumber;
        }

        public void setSeriesNumber(Integer seriesNumber) {
                this.seriesNumber = seriesNumber;
        }

        public LeagueRecord getLeagueRecord() {
                return leagueRecord;
        }

        public void setLeagueRecord(LeagueRecord leagueRecord) {
                this.leagueRecord = leagueRecord;
        }

}
