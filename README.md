# Tic-Tac-Toe

**Author:** James Kim

**Course:** CS 3560

---

## Overview

This project implements Tic-Tac-Toe in Java

---

## Design Rationale

### Encapsulation

* `Board` keeps its internal state fully private: `private final int size`, `private final Mark[][] grid`, and `private Mark currentTurn`.
* All mutations flow through a **single narrow method** `place(Move)`, which validates:

  * index bounds (centralized in a private `validateIndex(r,c)` helper),
  * correct turn (`currentTurn`),
  * cell occupancy (no overwrite).
* Invariants:

  * The grid is square (`size × size`),
  * Only one mark per cell,
  * Turns alternate `X → O → X ...` driven by `swapTurn()`.

### Inheritance & Polymorphism

* `Player` is an **abstract base** exposing `nextMove(Board)` and holding `mark`.
* `HumanPlayer` and `RandomAIPlayer` are **interchangeable subtypes**. The `Game` loop is written against the `Player` abstraction and does not know the concrete type at compile time — a direct use of **polymorphism**.

---

## UML (ASCII Sketch)

```
+------------------+        +------------------+
|      Board       |        |      Player      |<<abstract>>
|------------------|        |------------------|
| - grid: Mark[][] |        | - mark: Mark     |
| - size: int      |        |------------------|
| - currentTurn    |        | + nextMove(Board): Move |
|------------------|        | + getMark(): Mark        |
| + place(Move)    |        +------------------+
| + getCell()      |                ^
| + isFull()       |                |
| + winner()       |        +------------------+
| + reset()        |        |   HumanPlayer    |
+------------------+        +------------------+
        ^                            ^
        |                            |
+------------------+        +------------------+
|      Move        |        | RandomAIPlayer   |
|------------------|        +------------------+
| + row: int       |
| + col: int       |        enum Mark { X, O, EMPTY }
| + mark: Mark     |        class Game { run(): Optional<Mark> }
+------------------+
```

---

## How to Run

How to Run

Simply open the project in IntelliJ IDEA (or another IDE), right‑click ConsoleApp.java in src/main/java/cpp, and choose Run 'ConsoleApp.main()'.

To run tests, right‑click the test/java/cpp folder (or any test class) and choose Run 'All Tests'.

---

## Notable Code Excerpts

### 1) Debug-friendly symbol rendering

```java
public enum Mark {
    X, O, EMPTY;
    public String toSymbol() {
        return switch (this) {
            case X -> "X";
            case O -> "O";
            case EMPTY -> " ";
        };
    }
}
```

**Why it matters:** Simplifies console rendering and debugging by mapping enum values to human-readable symbols.


---
## Test Results

All tests were executed directly in IntelliJ IDEA using the built‑in JUnit 5 runner.
Both BoardTest and GameTest ran successfully with all test cases passing (green).

---

## Quick Notes for Reviewers

* Encapsulation is enforced by private fields and a single validated entry point (`place`).
* Polymorphism is exercised in `Game` via `Player` abstraction.
* Tests cover: bounds/turn/occupancy checks, row/column/diagonal wins, draw, and invalid-move retry behavior in the game loop.

---

## Future Work (optional)

* **SmartAIPlayer** (center > corners > edges, plus block-opponent heuristic)
* **NxN board** with configurable win condition (e.g., 3-in-a-row on N×N)
* **Undo/Redo** using `Deque<Move>`
