package objectville;

import java.util.LinkedList;
import java.util.Queue;

// Berkay
public class UtilityDistributor {


    private CityMap map;

    public UtilityDistributor(CityMap map) {
        this.map = map;
    }

    public void distribute(UtilityProvider provider) {
        int rows = map.getRows();
        int cols = map.getCols();
        int remaining = provider.getCapacity();

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        visited[provider.getRow()][provider.getCol()] = true;
        addNeighbors(provider.getRow(), provider.getCol(), visited, queue);

        while (!queue.isEmpty() && remaining > 0) {
            int[] pos = queue.poll();
            int r = pos[0];
            int c = pos[1];

            Cell cell = map.getCell(r, c);

            if (cell instanceof Zone) {
                Zone zone = (Zone) cell;
                int demand = provider.getDemandFrom(zone);
                if (demand > 0) {
                    int toGive = Math.min(demand, remaining);
                    provider.deliverTo(zone, toGive);
                    remaining -= toGive;
                }
                addNeighbors(r, c, visited, queue);
            } else if (cell instanceof Road) {
                addNeighbors(r, c, visited, queue);
            }
        }
    }

    // 8 yonlu: kardinal + capraz
    private void addNeighbors(int r, int c, boolean[][] visited, Queue<int[]> queue) {
        int rows = map.getRows();
        int cols = map.getCols();

        int[][] directions = {
                {-1,  0}, {1,  0}, {0, -1}, {0,  1},
                {-1, -1}, {-1, 1}, {1, -1}, {1,  1}
        };

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                if (!visited[nr][nc] && map.getCell(nr, nc).isConnectable()) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
}
