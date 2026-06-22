# Chess — Java OOP Implementation

A two-player chess game implemented in Java as a university assignment (2023/24). Built entirely with core Java — no external libraries. Demonstrates object-oriented design principles applied to a rules-complete chess engine.

---

## What It Does

- Full legal move validation for all six piece types
- Check and checkmate detection
- Castling (kingside and queenside)
- En passant
- Turn-based two-player gameplay via text interface
- Game state returned as structured enums: `LEGAL_MOVE`, `CHECK_WHITE`, `CHECK_BLACK`, `CHECKMATE_WHITE_WINS`, `CHECKMATE_BLACK_WINS`

---

## Design

The core design uses a `Piece` abstract base class with polymorphic `ableToMove()` and `canTravel()` methods overridden per piece type. The board is stored as a `HashMap<String, Piece>` keyed by algebraic notation (`"e4"`, `"d7"`), which makes position lookups and move execution straightforward.

```
Piece (abstract)
├── Pawn       — forward movement, two-step from starting rank, diagonal capture
├── Knight     — L-shaped movement, no path obstruction check needed
├── Bishop     — diagonal, path obstruction via canTravel()
├── Rook       — rank/file, path obstruction via canTravel()
├── Queen      — combines Rook + Bishop movement
└── King       — single step, castling rights tracked, checkmate detection
```

Check detection (`Board.isCheck`) scans the board for any opposing piece that can legally reach the king's square. Checkmate detection (`King.isInCheckmate`) iterates all king escape squares, temporarily places the king, and verifies whether check persists.

---

## How to Run

Requires Java 11+. Compile and run from the `chess/` directory:

```bash
javac chess/*.java
java chess.PlayChess
```

---

## Context

This project was built as a graded assignment focused on applying OOP design patterns — inheritance, polymorphism, and encapsulation — to a non-trivial domain. It has no computer opponent.

After completing this, I rebuilt chess from scratch in Python as a personal project ([ChessEngine](https://github.com/AlwaysTraid/ChessEngine)) — adding bitboard representation, a negamax alpha-beta search with quiescence, and a React/FastAPI web UI. The two repos together show the progression from structured coursework to independent systems design.