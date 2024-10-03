package beehive.rogueleague;

import java.util.Map;

public class CharacterLocator {
    private static MapGrid currentMapGrid;
    private static Map<Integer, Entity> entities;

    public static void init(MapGrid mapGrid, Map<Integer, Entity> entity) {
        entities = entity;
        currentMapGrid = mapGrid;
    }

    public static boolean getMapCollision(int x, int y) {
        return (currentMapGrid.getTile(x,y) != 0);
    }
    public static Entity getEntityCollision(int x, int y) {
        for(Integer i : entities.keySet()) {
            if(entities.get(i).getXPos() == x && entities.get(i).getYPos() == y) {
                return entities.get(i);
            }
        }
        return null;
    }
    //
}
