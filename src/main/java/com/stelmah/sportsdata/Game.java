package com.stelmah.sportsdata;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Game {

    // Teams involved in the game.
    private final Team homeTeam;
    private final Team awayTeam;

    // Current scores for each team.
    private int homeScore;
    private int awayScore;

    // The time when the game started.
    private LocalDateTime startTime;

    /**
     * Constructor to initialize a new game with the given teams.
     * The scores are initialized to 0, and the start time is set to the current time.
     */
    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
    }

    /**
     * Updates the score of the game.
     */
    public void setScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

}
