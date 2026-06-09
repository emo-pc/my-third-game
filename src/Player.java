public class Player {
    private Position pos;
    private int score;

    private Game.Direction currentDirection;
    private Game.Direction requestedDirection;

    private double visualRow;
    private double visualCol;

    private boolean moving;

    public Player(Position pos) {
        this.currentDirection = Game.Direction.NONE;
        this.requestedDirection = Game.Direction.NONE;
        this.pos = pos;
        this.score = 0;
        this.visualRow = pos.getRow();
        this.visualCol = pos.getCol();
        this.moving = false;

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Position getPos() {
        return pos;
    }

    public Game.Direction getCurrentDirection() {
        return currentDirection;
    }

    public double getVisualRow() {
        return visualRow;
    }

    public double getVisualCol() {
        return visualCol;
    }

    public int getScore() {
        return score;
    }

     public void incrementScore(){
        this.score+=10;
     }
    public void setRequestedDirection(Game.Direction direction){
        this.requestedDirection=direction;
    }
    public void update(MapData mapData) {
        if (isGridAligned()) {
            //if everything is ok pacman can change its direction
            if (requestedDirection != Game.Direction.NONE && mapData.isValidMove(pos.getRow() + requestedDirection.getDRow(), pos.getCol() + requestedDirection.getDCol())) {
                currentDirection = requestedDirection;
                moving = true;
            }
            if (currentDirection != Game.Direction.NONE) {
                //moving pacman according to direction
                int nextRow = pos.getRow() + currentDirection.getDRow();
                int nextCol = pos.getCol() + currentDirection.getDCol();

                if (mapData.isValidMove(nextRow, nextCol)) {
                    pos = new Position(nextRow, nextCol);
                    moving = true;
                } else {
                    moving = false;
                }
            }
        }
        if (moving) {
            //handling the visual movement
            visualRow += currentDirection.getDRow() * 0.10;
            visualCol += currentDirection.getDCol() * 0.10;
        }
    }
    private boolean isGridAligned() {
        return Math.abs(visualRow - pos.getRow()) < 0.01 && Math.abs(visualCol - pos.getCol()) < 0.01;
    }
    public void setPos(Position pos){
        this.pos=pos;
    }
    public void setRowCol(Position pos){
        this.visualCol=pos.getCol();
        this.visualRow=pos.getRow();
    }
    //resetting the player's dir ,score and move
    public void resetPlayer(){
        this.currentDirection = Game.Direction.NONE;
        this.requestedDirection = Game.Direction.NONE;
        this.score = 0;
        this.moving = false;
    }
}
