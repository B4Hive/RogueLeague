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
        float d = density/100F;
        MapGrid temp = new MapGrid(size);
        if(density < 0 || density > 0.3)
            d = 0.15F;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                temp.grid[i][j] = 1;

                switch (type) {
                    case "basic":
                        if(i > 0 && i < size-1 && j > 0 && j < size-1)
                            temp.grid[i][j] = 0;
                        break;
                    case "rift":
                        if(i > 0 && i < (size)*d || j > 0 && j < (size)*d)
                            temp.grid[i][j] = 0;
                        if(i >= size-(size*d) && i < size-1 || j >= size-(size*d) && j < size-1)
                            temp.grid[i][j] = 0;
                        if(i > j - (size*d) && i < j + (size*d))
                            temp.grid[i][j] = 0;
                        break;
                    case "random":
                        if(Math.random() > d)
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
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
