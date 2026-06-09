import java.util.ArrayList;

public class BFSPathFinder {
    public ArrayList<Position> getFullShortestPath(Position start, Position goal, MapData mapData) {
        //if start==goal return start
        if (start.equals(goal)) {
            ArrayList<Position> path = new ArrayList<>();
            path.add(start);
            return path;
        }
        Queue<Node> queue = new Queue<>();
        //array of visited squares
        boolean[][] visited = new boolean[mapData.getRows()][mapData.getCols()];
        //at first adding start to queue
        queue.enqueue(new Node(start, null));
        visited[start.getRow()][start.getCol()] = true;

        while (!queue.isEmpty()) {
            //processing the next element subsequently
            Node current = queue.dequeue();
            //if arrived to goal the shortest path is found
            if (current.pos.equals(goal)) {
                Node node=current;
                ArrayList<Position> path = new ArrayList<>();
                //constructing the path with chain mechanism
                while (node != null) {
                    path.add(0, node.pos);
                    node = node.parent;
                }
                return path;
            }
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] d : directions) {
                int newRow = current.pos.getRow() + d[0];
                int newCol = current.pos.getCol() + d[1];
                //for all directions if it is valid enqueueing the new pos of that dir with current is parent node
                if (mapData.isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.enqueue(new Node(new Position(newRow, newCol), current));
                }
            }
        }
        return null;
    }

    //helpful class
    private static class Node {
        Position pos;
        //the node from which this node is derived
        Node parent;
        Node(Position pos, Node parent) {
            this.pos = pos;
            this.parent = parent;
        }
    }
}
