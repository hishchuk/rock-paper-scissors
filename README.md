# Rock Paper Scissors

A console Rock-Paper-Scissors game that I wrote as a coding exercise, focusing on clean code and modern Java practices.

## What it does

Simple rock-paper-scissors against the computer. Best of 3 rounds, enter 1/2/3 for your moves, see who wins.

## Why I built it this way

I wanted to write simple but fast code, so I made some specific choices:

- **No string concatenation** - Used `.formatted()` everywhere instead of `+` operator
- **No System.out.println** - Everything goes through `System.Logger` for proper logging
- **Modern Java features** - Records instead of classes where possible, switch expressions, text blocks
- **No external dependencies** - Just standard Java 21, keeping it lightweight
- **Centralized strings** - All text in `GameMessages` constants to avoid hardcoded strings scattered around

The whole thing uses about 46 meaningful tests and follows some decent patterns like Strategy for different player types.

## Running it

Need Java 21+:

```bash
  mvn compile exec:java
```

Or build a jar:
```bash
  mvn package
  java -jar target/rock-paper-scissors-1.0.0.jar
```

Without Maven (manual compilation):
```bash
  javac -d target/classes src/main/java/com/imc/game/*.java src/main/java/com/imc/game/*/*.java
  java -cp target/classes com.imc.game.RockPaperScissorsApplication
```

## How to play

1. Type 1 for rock, 2 for paper, 3 for scissors
2. Computer picks randomly
3. Play 3 rounds, best score wins
4. Repeat if you want

## Code structure

```
domain/     - game logic (Move enum, GameResult record, etc.)
player/     - HumanPlayer and ComputerPlayer 
engine/     - GameEngine that runs rounds
service/    - MatchService for 3-round matches
constants/  - All the text strings in one place
```

## Testing

`mvn test`

Covers all the game logic, input validation, edge cases. Used JUnit 5 and Mockito where needed, but tried to keep most tests simple and focused.

The computer randomness is tested by running it until all three moves appear (usually takes way less than the 1000 attempt limit).

## Development

Built with:
- Java 21 (required)
- Maven 3.6+ (optional, can compile manually)
- Any IDE that supports Java 21

The code uses modern Java features like records and switch expressions, so older Java versions won't work.

All dependencies are test-only (JUnit 5, Mockito) - the runtime has zero external dependencies.

## Technical details

- Java 21 with modern features (records, switch expressions, text formatting)
- Zero runtime dependencies 
- Clean separation between user interface and system logging
- Strategy pattern for player types (easy to add network players later)
- Immutable data structures where possible
- All user input properly validated with helpful error messages

Built this as a take-home coding exercise to show clean Java practices without overengineering.
