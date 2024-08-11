package com.stelmah.sportsdata;

import com.stelmah.sportsdata.exception.ScoreBoardException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryScoreBoard implements ScoreBoard {

    // List to store the games that have started but not yet finished.
    private final List<Game> games = new ArrayList<>();

    // Lock to ensure thread-safe operations on the games list
    private final Lock gameLock = new ReentrantLock();

    public void startGame(Team homeTeam, Team awayTeam) {
        validateTeam(homeTeam);
        validateTeam(awayTeam);

        gameLock.lock();
        try {
            validateIfGameAlreadyStarted(homeTeam, awayTeam);
            Game game = new Game(homeTeam, awayTeam); // Simplified GameFactory model
            games.add(game);
        } finally {
            gameLock.unlock();
        }

    }

    public void finishGame(Team homeTeam, Team awayTeam) {
        validateTeam(homeTeam);
        validateTeam(awayTeam);

        gameLock.lock();
        try {
            Game activeGame = games.stream()
                    .filter(game -> game.getHomeTeam().equals(homeTeam))
                    .filter(game -> game.getAwayTeam().equals(awayTeam))
                    .findFirst()
                    .orElseThrow(() -> new ScoreBoardException("Game not found"));

            games.remove(activeGame);
        } finally {
            gameLock.unlock();
        }
    }

    public void updateGame(Team homeTeam, Team awayTeam, int homeScore, int awayScore) {
        validateTeam(homeTeam);
        validateTeam(awayTeam);
        validateScore(homeScore);
        validateScore(awayScore);

        Game activeGame = games.stream()
                .filter(game -> game.getHomeTeam().equals(homeTeam))
                .filter(game -> game.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new ScoreBoardException("Game not found"));

        activeGame.setScore(homeScore, awayScore);
    }

    public List<GameSummary> getGameSummary() {
        return games.stream()
                .map(GameSummary::new) // Simplified GameSummaryFactory model
                .sorted()
                .toList();
    }

    /**
     * Checks if a game between the same home and away teams is already in progress.
     * If such a game is found, a {@link ScoreBoardException} is thrown to indicate
     * that the game has already started.
     */
    private void validateIfGameAlreadyStarted(Team homeTeam, Team awayTeam) {
       games.stream()
               .filter(game -> game.getHomeTeam().equals(homeTeam))
               .filter(game -> game.getAwayTeam().equals(awayTeam))
               .findFirst()
               .ifPresent((game) -> {
                   throw new ScoreBoardException("Game already started");
               });
    }

    /**
     * Validates a team to ensure it is not null and has a valid name.
     */
    private void validateTeam(Team team) {
        if (team == null) {
            throw new ScoreBoardException("Team cannot be null");
        }
        if (team.getName() == null) {
            throw new ScoreBoardException("Team name cannot be null");
        }
    }

    /**
     * Validates a score to ensure it is not negative.
     */
    private void validateScore(int score) {
        if (score < 0) {
            throw new ScoreBoardException("Score cannot be negative");
        }
    }
}
