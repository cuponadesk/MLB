package com.MajorLeagueAPI.MLB;

import com.MajorLeagueAPI.MLB.Linescore.Linescore;
import com.MajorLeagueAPI.MLB.Schedule.Game;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MLB {

	GameState state = new GameState();

	//timer stuff
	Timer timer;
	int timerDelay = 60 * 1000;

	public static final String API_BASE_URL = "http://statsapi.mlb.com/";
	public static final String USE_TODAY = "USE_TODAYS_DATE_AS_QUERY_PARAM";
	protected static final String API_TIMESTAMPS_PREFIX = "api/v1.1/game/{gamePk}/feed/live/timestamps";
	public static final String API_LINESCORE_PREFIX = "api/v1/game/{gamePk}/linescore?timecode=";
	private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000 * 15;

	private ArrayList<String> timeStamps = new ArrayList<>();
	private static RestTemplate restTemplate = new RestTemplate();

	boolean timerStarted = false;
	boolean gameStateUpdate = false;

	private final String teamName;

	MLBAPI api;
	Linescore linescore;

	private Integer todayGamePk;

	private Game todaysGame;

	public MLB(String teamName) {
		this.teamName = teamName;
		api = new MLBAPI();

	}


	public void startUpdateTimer(int delay) {
		this.timerDelay = delay;
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.setRepeats(true);
		timer.setDelay(timerDelay);
		timer.start();
		timerStarted = true;
		System.out.println("Timer started");
	}

	public void stopUpdateTimer() {
		timerStarted = false;
		if (timer != null) {
			timer.stop();
		}
	}

	public boolean isTimerStarted() {
		return timerStarted;
	}

	public boolean isGameToday() {

		boolean isGame = (api.getGameToday(teamName) != null);

		if (isGame) {
			todaysGame = api.getGameToday(teamName);
			todayGamePk = api.getGameToday(teamName).getGamePk();
		}
		return isGame;

	}


	public boolean isGameStarted() {
		return todaysGame.getStatus().getAbstractGameCode() == 'L';
	}

	/**
	 * Checks for new timestamp from MLB API for active game
	 *
	 * @return True if new timestamp
	 */
	private boolean checkForNewTimestamp() {

		int oldTimestampSize = timeStamps.size();


		timeStamps = restTemplate.getForObject(API_BASE_URL + replaceGamePKInString(API_TIMESTAMPS_PREFIX, todayGamePk.toString()), ArrayList.class);
		try {
			if (timeStamps.size() != oldTimestampSize) {
				System.out.println("New timestamp");
				return true;
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer.");
		} catch (Exception ex) {
			System.out.println("ex");
		}
		return false;
	}


	public boolean tick() {
		System.out.println("Tick");
		if (checkForNewTimestamp()) {
			updateGameState();
			return true;
		}
		return false;

	}

	private void getNewLinescoreData(String timestamp, String gamePk) {
		linescore = restTemplate.getForObject(API_BASE_URL + replaceGamePKInString(API_LINESCORE_PREFIX, gamePk) + timestamp, Linescore.class);
	}

	private String replaceGamePKInString(String urlString, String gamePK) {
		return urlString.replace("{gamePk}", gamePK);
	}


	public GameState updateGameState() {
		if (timeStamps.size() > 0) {
			getNewLinescoreData(timeStamps.get(timeStamps.size() - 1), Integer.toString(todayGamePk));

			state.setInning(linescore.getCurrentInning());
			state.setInningHalfWord(linescore.getInningState());
			switch (linescore.getInningState()) {
				case "Top":
					state.setInningHalf(0);
					break;
				case "Middle":
					state.setInningHalf(1);
					break;
				case "Bottom":
					state.setInningHalf(2);
					break;
				case "End":
				default:
					state.setInningHalf(3);
					break;
			}

			if (state.getInningHalf() % 2 == 0) {
				state.setBalls(linescore.getBalls());
				state.setStrikes(linescore.getStrikes());
				state.setOuts(linescore.getOuts());

				state.setFirst(linescore.getOffense().getFirst() != null);
				state.setSecond(linescore.getOffense().getSecond() != null);
				state.setThird(linescore.getOffense().getThird() != null);
			} else {
				state.setBalls(0);
				state.setStrikes(0);
				state.setOuts(0);

				state.setFirst(false);
				state.setSecond(false);
				state.setThird(false);
			}

			state.setBatting(linescore.getOffense().getBatter().getFullName());
			state.setPitching(linescore.getDefense().getPitcher().getFullName());

			state.setHomeErrors(linescore.getTeams().getHome().getErrors());
			state.setHomeHits(linescore.getTeams().getHome().getHits());
			state.setHomeRuns(linescore.getTeams().getHome().getRuns());

			state.setAwayErrors(linescore.getTeams().getAway().getErrors());
			state.setAwayHits(linescore.getTeams().getAway().getHits());
			state.setAwayRuns(linescore.getTeams().getAway().getRuns());
		}

//      state.setHomeName( api.getTeams().getLocationFromId( api.getGameToday(teamName).getTeams().getHome().getTeam().getId()) + " " + api.getTeams().getTeamNameFromId( api.getGameToday(teamName).getTeams().getHome().getTeam().getId() ) );
//      state.setAwayName( api.getTeams().getLocationFromId( api.getGameToday(teamName).getTeams().getAway().getTeam().getId()) + " " +  api.getTeams().getTeamNameFromId( api.getGameToday(teamName).getTeams().getAway().getTeam().getId() ) );

		state.setAwayName(api.getTeams().getFullTeamNameFromId(api.getGameToday(teamName).getTeams().getAway().getTeam().getId()));
		state.setHomeName(api.getTeams().getFullTeamNameFromId(api.getGameToday(teamName).getTeams().getHome().getTeam().getId()));

		System.out.println("Game state updated");
		System.out.println(state.toString());

		return state;
	}

	public String getTeamNameFromId(int id) {
		return api.getTeams().getTeamNameFromId(id);
	}

	public String getTeamLocationFromid(int id) {
		return api.getTeams().getLocationFromId(id);
	}

}
