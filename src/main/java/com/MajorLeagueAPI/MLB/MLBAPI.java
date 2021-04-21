package com.MajorLeagueAPI.MLB;

import com.MajorLeagueAPI.MLB.Schedule.Game;
import com.MajorLeagueAPI.MLB.Schedule.Schedule;
import com.MajorLeagueAPI.MLB.Team.Teams;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.MajorLeagueAPI.MLB.MLB.API_BASE_URL;

public class MLBAPI {


	private static final String API_SCHEDULE_PREFIX = "api/v1/schedule/games?&sportId=1";
	private static final String API_SCHEDULE_START_DATE_PREFIX = "&startDate=";
	private static final String API_SCHEDULE_END_DATE_PREFIX = "&endDate=";
	private static final String API_LINESCORE_PREFIX = "api/v1/game/{gamePk}/linescore";
	private static final String API_TEAMS_PREFIX = "api/v1/teams?sportId=1";
	private static final int UPDATE_DELAY = 1000 * 60 * 5;
	private static RestTemplate restTemplate = new RestTemplate();

	private Schedule schedule = new Schedule();
	private Teams teams;

	private Timer t;

	public MLBAPI() {
		populateTodayScheduleData();
		updateTimer();
		populateTeamsData();
	}


	private void updateTimer() {
		t = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				populateTodayScheduleData();
			}
		});
		t.setRepeats(true);
		t.setDelay(UPDATE_DELAY);
		t.start();
	}





	public Game getGameToday(String teamName) {
		try {
			for (Game g : schedule.getDates().get(0).getGames()) {
				if (g.getTeams().getAway().getTeam().getName().equals(teamName) || g.getTeams().getHome().getTeam().getName().equals(teamName)) {
					return g;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

   /*//////////////////////////////////////////
   END PUBLIC METHODS
   *//////////////////////////////////////////

   /*//////////////////////////////////////////
   START PRIVATE METHODS
   *//////////////////////////////////////////

	public void populateTodayScheduleData() {
		System.out.println(API_BASE_URL + API_SCHEDULE_PREFIX);
		schedule = restTemplate.getForObject(API_BASE_URL + API_SCHEDULE_PREFIX, Schedule.class);
	}

	/*Populate MLBBase from provided date(s)*/

	private void populateTeamsData() {
		teams = restTemplate.getForObject(API_BASE_URL + API_TEAMS_PREFIX, Teams.class);
	}



   /*//////////////////////////////////////////
   END PRIVATE METHODS
   *//////////////////////////////////////////

	protected String dateObjectToString(Date day) {
		String pattern = "MM/dd/yyyy";
		return new SimpleDateFormat(pattern).format(day);
	}

	public Teams getTeams() {
		return teams;
	}
}
