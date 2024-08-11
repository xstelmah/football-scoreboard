# Football World Cup Score Board

## Overview

This project is a simple, in-memory implementation of a Football World Cup Score Board. It allows tracking live football scores for ongoing games, with the ability to start games, update scores, finish games, and retrieve a summary of all ongoing games sorted by total score and start time. The solution is designed with simplicity, adherence to SOLID principles, and test-driven development (TDD) in mind.

## Features

The scoreboard supports the following operations:

1. **Start a Game**: 
   - Adds a new game to the scoreboard.
   - The initial score is set to 0-0 for both teams.
   - Validates that a game between the same teams is not already in progress.

2. **Finish a Game**:
   - Removes a game from the scoreboard.
   - Ensures the game exists before attempting to finish it.

3. **Update Score**:
   - Updates the score for a specified game.
   - Ensures the game exists and that scores are valid (non-negative).

4. **Get Summary**:
   - Provides a summary of all ongoing games.
   - Games are sorted by total score in descending order.
   - Games with the same total score are further sorted by start time, with the most recent games listed first.

## Example

Given the following ongoing games:

- Mexico - Canada: 0 – 5
- Spain - Brazil: 10 – 2
- Germany - France: 2 – 2
- Uruguay - Italy: 6 – 6
- Argentina - Australia: 3 - 1

The scoreboard will provide the following summary:

1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

## Design Considerations

- **In-Memory Storage**: 
  - The solution uses simple Java collections (`ArrayList`) for in-memory storage, making it suitable for small-scale applications without the need for persistence.
  - Thread safety is ensured using `ReentrantLock` to handle concurrent operations on the games list.

- **Object-Oriented Design**:
  - The design adheres to SOLID principles, ensuring that the system is maintainable, extensible, and testable.
  - Separation of concerns is maintained, with each class handling a specific responsibility.

- **Test-Driven Development (TDD)**:
  - All functionalities are covered by unit tests to ensure correctness and reliability.
  - The tests are organized to cover both typical and edge cases, ensuring robustness.

## Assumptions

- **Teams Validation**: 
  - The system assumes that team names are non-null and unique. If a team without a name or with a duplicate name is provided, an exception is thrown.
  
- **Unique Games**:
  - The system assumes that there can only be one game in progress between the same two teams at any given time.

- **In-Memory Limitation**:
  - The implementation is designed for simplicity and does not include persistent storage. All data will be lost when the application stops.

## How to Use

### Prerequisites

- **Java**: Ensure you have Java 21 or higher installed.
- **Maven**: The project uses Maven for build management and dependency resolution.

### Build and Run

1. Clone the repository:

   ```bash
   git clone https://github.com/xstelmah/live-scoreboard.git
   cd live-scoreboard
   ```

2. Build the project and run tests:

   ```bash
   mvn clean test
   ```

3. To use the scoreboard in your own Java application, include the `InMemoryScoreBoard` class and its dependencies.

### Example Code

```java
ScoreBoard scoreBoard = new InMemoryScoreBoard();
Team teamA = new Team("Team A");
Team teamB = new Team("Team B");

scoreBoard.startGame(teamA, teamB);
scoreBoard.updateGame(teamA, teamB, 1, 2);

List<GameSummary> summaries = scoreBoard.getGameSummary();
for (GameSummary summary : summaries) {
    System.out.println(summary);
}
```

## Future Enhancements

- **Persistent Storage**: Integrating a database for persistent storage of games, allowing the scoreboard to retain data between application restarts.
- **REST API**: Creating a RESTful API to interact with the scoreboard remotely.
- **Improved Validation**: Adding more sophisticated validation mechanisms for input data (e.g., checking for invalid team names or scores).

## Conclusion

This simple Football World Cup Score Board implementation is designed to be easy to understand, maintain, and extend. It leverages the principles of object-oriented design and test-driven development to ensure a high-quality solution. The project is a foundational piece that can be expanded with additional features or integrated into larger systems.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
