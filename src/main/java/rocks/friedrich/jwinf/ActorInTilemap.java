package rocks.friedrich.jwinf;

import ea.Direction;

public class ActorInTilemap extends Actor {
  protected LetterTileMap map;

  public ActorInTilemap(String filepath, LetterTileMap map) {
    super(filepath);
    this.map = map;

    // grid edges
    addMovementListener((int x, int y, Direction direction) -> {
      switch (direction) {
        case RIGHT:
          return x < map.getWidth() - 1;

        case UP:
          return y < map.getHeight() - 1;

        case LEFT:
          return x > 0;

        case DOWN:
          return y > 0;

        default:
          return true;
      }
    });

    // Obstacles
    addMovementListener((int x, int y, Direction direction) -> {
      int xMovement = 0;
      int yMovement = 0;

      switch (direction) {
        case RIGHT:
          xMovement = 1;
          break;

        case UP:
          yMovement = 1;
          break;

        case LEFT:
          xMovement = -1;
          break;

        case DOWN:
          yMovement = -1;
          break;

        default:
      }

      return !map.isObstacle(x + xMovement, y + yMovement);
    });

  }

  /**
   * Gib den Buchstaben der Kachel zurück, auf dem sich das Objekt gerade
   * befindet.
   */
  public char getTile() {
    return map.getLetter(getGridX(), getGridY());
  }

  public boolean canMove(Direction direction) {
    boolean result = super.canMove(direction);
    if (!result) {
      wiggle();
    }
    return result;
  }

}
