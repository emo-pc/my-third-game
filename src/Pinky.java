public class Pinky extends Enemy {

    public Pinky(Position pos) {
        super(pos);
    }
    @Override
    public Position selectTarget(Player player, MapData mapData) {
        //just players pos
        return player.getPos();
    }
}
