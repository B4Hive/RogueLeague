package beehive.rogueleague;

/**
 *
 * @author BeeHive
 */

public class MapGrid {
    private int size;
    private int[][] grid;

    private MapGrid(int size) {
        this.size = size;
        grid = new int[size][size];
    }

    public int getSize(){
        return size;
    }

    public int getTile(int x, int y){
        return grid[x][y];
    }

    public static MapGrid create(int size, String type, int density) {
        int d = size * density / 100;
        MapGrid temp = new MapGrid(size);
        if(density < 2 || density > 30)
            d = 15;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                temp.grid[i][j] = 1;

                switch (type) {
                    case "basic":
                        if(i > 0 && i < size-1 && j > 0 && j < size-1)
                            temp.grid[i][j] = 0;
                        break;
                    case "rift":
                        if(i > 0 && i < d || j > 0 && j < d)
                            temp.grid[i][j] = 0;
                        if(i >= size - d && i < size-1 || j >= size - d && j < size-1)
                            temp.grid[i][j] = 0;
                        if(i > j - d && i < j + d)
                            temp.grid[i][j] = 0;
                        break;
                    case "random":
                        if(Math.random()*100 > d)
                            temp.grid[i][j] = 0;
                        break;
                }

                if(i == 0 || i == size-1 || j == 0 || j == size-1)
                    temp.grid[i][j] = 1;
            }
        }

        return temp;
    }

    public void printGrid(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

}
