package com.stelmah.sportsdata;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameSummary implements Comparable<GameSummary> {

    // Format for displaying the game summary.
    private static final String DISPLAY_FORMAT = "%s %d - %s %d";

    private final String homeTeamName;
    private final String awayTeamName;

    private final int homeScore;
    private final int awayScore;

    private final LocalDateTime startTime;

    public GameSummary(Game game) {
        this.homeTeamName = game.getHomeTeam().getName();
        this.awayTeamName = game.getAwayTeam().getName();
        this.homeScore = game.getHomeScore();
        this.awayScore = game.getAwayScore();
        this.startTime = game.getStartTime();
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    @Override
    public String toString() {
        return String.format(DISPLAY_FORMAT, homeTeamName, homeScore, awayTeamName, awayScore);
    }

    /**
     * Compares this game summary to another game summary for sorting.
     * The comparison is first based on total score (descending), then by start time (descending).
     */
    @Override
    public int compareTo(GameSummary other) {
        // First, compare by total score in descending order (reversed)
        int scoreComparison = Integer.compare(other.getTotalScore(), this.getTotalScore());

        // If the total scores are the same, compare by start time in descending order (reversed)
        if (scoreComparison == 0) {
            return other.getStartTime().compareTo(this.startTime);
        }

        return scoreComparison;
    }
}
