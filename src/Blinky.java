public class Blinky extends Enemy {

    public Blinky(Position pos) {
        super(pos);
    }
    @Override
    public Position selectTarget(Player player, MapData mapData) {
        Position[] corners = mapData.getCorners();
        Position closest = corners[0];
        double minDistance = Double.MAX_VALUE;
        //finding the shortest corner
        for (Position corner : corners) {
            //calculating distance between corner and player
            double dist = Math.sqrt(Math.pow(corner.getRow() - player.getPos().getRow(), 2) +
                    Math.pow(corner.getCol() - player.getPos().getCol(), 2));

            if (dist < minDistance) {
                minDistance = dist;
                closest = corner;
            }
        }
        return closest;
    }

}
