import java.util.ArrayList;

public abstract class Enemy {
    protected Position pos;
    protected Game.Direction direction;
    protected BFSPathFinder finder;

    protected double visualRow;
    protected double visualCol;

    public Enemy(Position pos){
        this.pos = pos;
        this.direction = Game.Direction.NONE;
        this.visualRow = pos.getRow();
        this.visualCol = pos.getCol();
        this.finder=new BFSPathFinder();
    }

    public Enemy(Position pos, BFSPathFinder finder) {
        this.pos = pos;
        this.finder = finder;
        this.direction = Game.Direction.NONE;
        this.visualRow = pos.getRow();
        this.visualCol = pos.getCol();
    }

    public double getVisualRow() {
        return visualRow;
    }

    public double getVisualCol() {
        return visualCol;
    }

    public Game.Direction getDirection() {
        return direction;
    }

    public Position getPos() {
        return pos;
    }
    public void move(Player player,MapData mapdata){
        if (Math.abs(visualRow - pos.getRow()) < 0.01 && Math.abs(visualCol - pos.getCol()) < 0.01) {
            visualRow = pos.getRow();
            visualCol = pos.getCol();
            //selecting the target
            Position target = selectTarget(player, mapdata);
            //finding the shortest path to target
            ArrayList<Position> path = finder.getFullShortestPath(pos, target, mapdata);

            if (path != null && path.size() > 1) {
                Position nextStep = path.get(1);
                //finding the direction between pos and nextStep
                direction = getDirectionFromPositions(pos, nextStep);
                //updating the pos
                pos = nextStep;
            }
            else {
                direction = Game.Direction.NONE;
            }
        }
        if (direction != Game.Direction.NONE) {
            //handling the visual movement
            visualRow += direction.getDRow() * 0.067;
            visualCol += direction.getDCol() * 0.067;
        }
    }
    //this method indicates the direction between two neighbour square
    protected Game.Direction getDirectionFromPositions(Position from,Position to){
        int dRow = to.getRow() - from.getRow();
        int dCol = to.getCol() - from.getCol();

        if (dRow == -1) return Game.Direction.UP;
        if (dRow == 1)  return Game.Direction.DOWN;
        if (dCol == -1) return Game.Direction.LEFT;
        if (dCol == 1)  return Game.Direction.RIGHT;
        return Game.Direction.NONE;
    }
    public void setPos(Position pos){
        this.pos=pos;
    }
    public void setRowCol(Position pos){
        this.visualRow=pos.getRow();
        this.visualCol=pos.getCol();
    }
    public void setDir(Game.Direction dir){
        this.direction=dir;
    }
    abstract Position selectTarget(Player player, MapData mapData);
}
