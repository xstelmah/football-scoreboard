package com.stelmah.sportsdata;

import java.util.List;

public interface ScoreBoard {

    /**
     * Starts a new game between the specified home and away teams.
     *
     * @param homeTeam The home team.
     * @param awayTeam The away team.
     */
    void startGame(Team homeTeam, Team awayTeam);

    /**
     * Finishes an ongoing game between the specified home and away teams.
     *
     * @param homeTeam The home team.
     * @param awayTeam The away team.
     */
    void finishGame(Team homeTeam, Team awayTeam);

    /**
     * Updates the score of an ongoing game between the specified home and away teams.
     *
     * @param homeTeam  The home team.
     * @param awayTeam  The away team.
     * @param homeScore The new score for the home team.
     * @param awayScore The new score for the away team.
     */
    void updateGame(Team homeTeam, Team awayTeam, int homeScore, int awayScore);

    /**
     * Retrieves a summary of all ongoing games.
     *
     * @return A list of game summaries.
     */
    List<GameSummary> getGameSummary();

}
