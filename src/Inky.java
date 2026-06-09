import java.util.ArrayList;

public class Inky extends Enemy {

    public Inky(Position pos) {
        super(pos);
    }
    @Override
    public Position selectTarget(Player player, MapData mapData) {
        double num = Math.random();
        //60 percent chance player's pos
        if (num < 0.60) {
            return player.getPos();
        }
        //40 percent chance random dir
        else {
            int[][] neighbors = {{-1,0}, {1,0}, {0,-1}, {0,1}};
            ArrayList<Position> validNeighbors = new ArrayList<>();
            for (int[] d : neighbors) {
                //if there is no wall at that direction add to arraylist
                if (mapData.isValidMove(pos.getRow() + d[0], pos.getCol() + d[1])) {
                    validNeighbors.add(new Position(pos.getRow() + d[0], pos.getCol() + d[1]));
                }
            }
            //if there is no valid move, stay
            if (validNeighbors.isEmpty()) return pos;
            return validNeighbors.get((int)(Math.random() * validNeighbors.size()));
        }
    }
}
