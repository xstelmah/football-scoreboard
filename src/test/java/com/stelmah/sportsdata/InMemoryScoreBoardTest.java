package com.stelmah.sportsdata;

import com.stelmah.sportsdata.exception.ScoreBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryScoreBoardTest {

    private InMemoryScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new InMemoryScoreBoard();
    }

    @Test
    public void testStartGame() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        scoreBoard.startGame(homeTeam, awayTeam);

        List<GameSummary> summaries = scoreBoard.getGameSummary();
        assertEquals(1, summaries.size());
        assertEquals("Team A 0 - Team B 0", summaries.get(0).toString());
    }

    @Test
    public void testStartGame_NullTeam() {
        Team homeTeam = null;
        Team awayTeam = new Team("Team B");

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(homeTeam, awayTeam));
    }

    @Test
    public void testStartGame_TeamWithoutName() {
        Team homeTeam = new Team(null);
        Team awayTeam = new Team("Team B");

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(homeTeam, awayTeam));
    }

    @Test
    public void testStartGame_TwoGamesWithSameTeams() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        scoreBoard.startGame(homeTeam, awayTeam);

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(homeTeam, awayTeam));
    }

    @Test
    public void testFinishGame() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        scoreBoard.startGame(homeTeam, awayTeam);
        scoreBoard.finishGame(homeTeam, awayTeam);

        List<GameSummary> summaries = scoreBoard.getGameSummary();
        assertEquals(0, summaries.size());
    }

    @Test
    public void testFinishGame_NotFound() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        assertThrows(ScoreBoardException.class, () -> scoreBoard.finishGame(homeTeam, awayTeam));
    }

    @Test
    public void testUpdateGame() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        scoreBoard.startGame(homeTeam, awayTeam);
        scoreBoard.updateGame(homeTeam, awayTeam, 2, 3);

        List<GameSummary> summaries = scoreBoard.getGameSummary();
        assertEquals(1, summaries.size());
        assertEquals("Team A 2 - Team B 3", summaries.get(0).toString());
    }

    @Test
    public void testUpdateGame_NegativeScore() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        scoreBoard.startGame(homeTeam, awayTeam);
        assertThrows(ScoreBoardException.class, () -> scoreBoard.updateGame(homeTeam, awayTeam, -1, 3));
    }

    @Test
    public void testUpdateGame_NotFound() {
        Team homeTeam = new Team("Team A");
        Team awayTeam = new Team("Team B");

        assertThrows(ScoreBoardException.class, () -> scoreBoard.updateGame(homeTeam, awayTeam, 2, 3));
    }

    @Test
    public void testGetGameSummary() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        Team teamC = new Team("Team C");
        Team teamD = new Team("Team D");

        scoreBoard.startGame(teamA, teamB);
        scoreBoard.updateGame(teamA, teamB, 1, 2);
        scoreBoard.startGame(teamC, teamD);
        scoreBoard.updateGame(teamC, teamD, 3, 1);

        List<GameSummary> summaries = scoreBoard.getGameSummary();
        assertEquals(2, summaries.size());

        // The summary should be sorted by total score (descending) and start time (descending)
        assertEquals("Team C 3 - Team D 1", summaries.get(0).toString());
        assertEquals("Team A 1 - Team B 2", summaries.get(1).toString());
    }
}
