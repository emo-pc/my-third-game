public class Game {

    public enum GameState {
        START_SCREEN,
        READY,
        PLAYING,
        PAUSED,
        WON,
        LOST,
    }

    public enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), NONE(0, 0);

        private final int dRow;
        private final int dCol;

        Direction(int dRow, int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }

        public int getDRow() { return dRow; }
        public int getDCol() { return dCol; }
    }

    private final Player player;
    private final Enemy[] enemies;
    private GameState gameState;
    private final MapData mapData;

    public Game(Player player, Enemy[] enemies, MapData mapData) {
        this.player = player;
        this.enemies = enemies;
        this.mapData = mapData;
        this.gameState = GameState.START_SCREEN;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState newState) {
        this.gameState = newState;
    }
    public void update() {
        if (gameState != GameState.PLAYING) return;
        //update player
        player.update(mapData);
        //checking pacman-pellet consumption
        handlePelletConsumption();
        //checking pacman-enemy collision
        for (Enemy enemy : enemies) {
            enemy.move(player, mapData);
            if (checkCollision(player, enemy)) {
                gameState = GameState.LOST;
            }
        }
        //if there is no pellet left player has won the game
        if (isWon()){
            gameState=GameState.WON;
        }

    }

    private boolean checkCollision(Player p, Enemy e) {
        double dist = Math.sqrt(Math.pow(p.getVisualRow() - e.getVisualRow(), 2)+Math.pow(p.getVisualCol() - e.getVisualCol(), 2));
        return dist<0.5;
    }

    private void handlePelletConsumption() {
        for (int r=0;r< mapData.getRows();r++){
            for (int c=0;c< mapData.getCols();c++){
                if (mapData.hasPellet(r, c)) {
                    //if player's row and col equals to pellet's row and col pacman has eaten the pellet
                    if (player.getPos().getRow()==r&&player.getPos().getCol()==c) {
                        mapData.removePellet(r, c);
                        player.incrementScore();
                    }
                }
            }
        }
    }
    private boolean isWon(){
        for (int r=0;r< mapData.getRows();r++){
            for (int c=0;c< mapData.getCols();c++){
                if (mapData.hasPellet(r, c)) {
                    return false;
                }
                else continue;
            }
        }
        //if there is no pellet left winning the game
        return true;
    }
    public void resetGame(){
        //reset map
        mapData.resetMap();
        //reset player
        player.setPos(mapData.getPlayerStart());
        player.setRowCol(mapData.getPlayerStart());
        player.resetPlayer();
        //reset enemy
        for (Enemy enemy : enemies) {
            if (enemy instanceof Pinky) {
                enemy.setPos(mapData.getPinkyStart());
                enemy.setRowCol(mapData.getPinkyStart());
                enemy.setDir(Direction.NONE);
            }
            else if (enemy instanceof Inky) {
                enemy.setPos(mapData.getInkyStart());
                enemy.setRowCol(mapData.getInkyStart());
                enemy.setDir(Direction.NONE);
            }
            else if (enemy instanceof Blinky) {
                enemy.setPos(mapData.getBlinkyStart());
                enemy.setRowCol(mapData.getBlinkyStart());
                enemy.setDir(Direction.NONE);
            }
        }
        //reset game state
        setGameState(GameState.START_SCREEN);

    }

}