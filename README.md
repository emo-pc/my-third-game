# 🟡 Pac-Man 2D — BFS Pathfinding & Enemy AI

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![StdDraw](https://img.shields.io/badge/Library-StdDraw-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

A nostalgic 2D Pac-Man game implementation in Java using the **StdDraw** library. Developed for **Bogazici University CMPE 160 (Object-Oriented Programming)**.

The project features smooth grid-aligned movement, custom collision hitboxes, state machine game logic, and a **Breadth-First Search (BFS)** pathfinding engine running on a custom generic FIFO Queue data structure.

---
## 🎬 Gameplay Demo

🎥 **Watch Gameplay Video:** [Watch on YouTube](https://youtu.be/bP92kn4G6ug)

---
## 🕹️ Game Features & Enemy AI

### 1. BFS Pathfinding Engine
- **Custom Generic Queue (`Queue<T>`):** A dynamic, resizable circular FIFO queue built from scratch to support path exploration without standard Java collections.
- **Node Chaining Algorithm:** Explores the maze layer-by-layer to construct the absolute shortest path (`BFSPathFinder`) dynamically on every frame.

### 2. Specialized Ghost Behaviors
Each ghost extends the abstract `Enemy` class and implements a unique target selection strategy (`selectTarget`):

| Ghost | Targeting Strategy | Behavior |
| :--- | :--- | :--- |
| **Blinky** | Nearest Corner | Calculates distances to all map corners and targets the closest corner to Pac-Man. |
| **Pinky** | Direct Pursuit | Constantly targets Pac-Man's exact grid position. |
| **Inky** | Probabilistic AI | 60% chance to target Pac-Man directly, 40% chance to move towards a random valid adjacent path. |

### 3. Movement & Input Buffering
- **Grid Alignment (`isGridAligned`):** Pac-Man can only change directions when perfectly centered on a grid tile, preventing wall sticking and out-of-bounds sliding.
- **Visual vs. Logical Positions:** Decoupled grid positions (`Position`) and double-precision rendering offsets (`visualRow`, `visualCol`) for smooth sub-tile movement animations.

---

## 🎮 Controls & Game States

| Key | Action |
| :--- | :--- |
| `Arrow Keys (↑ ↓ ← →)` | Buffer Movement Direction |
| `Space` | Start Game / Transition from `START` to `READY` State |
| `P` | Pause / Unpause Game |
| `R` | Reset Game |
| `Q` | Quit Game |

### Supported States (`GameState` Enum)
`START` ➔ `READY` (1-sec delay) ➔ `PLAYING` ⇄ `PAUSED` ➔ `WIN` / `LOST`

---

## 🛠️ System Architecture

The codebase follows a modular Object-Oriented design:

```text
third-game/
├── Main.java          # Entry point, file parsing (map.txt), main game loop
├── Game.java          # State machine, collision detection, score & pellet tracking
├── GameRenderer.java  # StdDraw canvas scaling, sprite animation ticks, HUD rendering
├── MapData.java       # Immutable original map layout, pellet states, grid dimensions
├── Player.java        # Pac-Man entity (Input processing, grid alignment, visual updates)
├── Position.java      # Coordinate encapsulation and distance metrics
├── Queue.java         # Generic FIFO array-based queue with dynamic resizing
├── BFSPathFinder.java # Layer-by-layer shortest path algorithm using Node chaining
└── Enemy.java         # Abstract base ghost class (Extended by Blinky, Inky, Pinky)
```
## 🚀 How to Run Locally

### Prerequisites
- Java Development Kit (JDK 11 or higher) installed.

### Execution
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/emo-pc/my-third-game.git](https://github.com/emo-pc/my-third-game.git)
   cd my-third-game
   ```

2. **Compile and Run:**
   `stdlib.jar` is on the repo.
   Make sure you have the assets.
   Ensure `stdlib.jar` is in your working directory, then run:
   ```bash
   javac -cp .:stdlib.jar src/*.java -d bin
   java -cp bin:stdlib.jar Main
   ```

## 👤 Author

<a href="https://github.com/emo-pc">
  <img src="https://github.com/user-attachments/assets/7530def7-4d03-4244-974d-7545c44f81f1"" width="150" align="right" alt="Emre Ezgü Ghibli Portrait">
</a>

**Emre Ezgü**  
- **University:** Bogazici University — Computer Engineering
- **GitHub:** [@emo-pc](https://github.com/emo-pc)
