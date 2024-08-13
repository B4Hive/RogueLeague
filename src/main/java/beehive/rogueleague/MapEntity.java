package beehive.rogueleague;

/**
 *
 * @author BeeHive
 */

public class MapEntity {
    private int xPos;
    private int yPos;
    private final int id;

    public MapEntity(int xPos, int yPos, int id) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
    }

    public int getXPos() {
        return xPos;
    }
    private void setXPos(int xPos) {
        this.xPos = xPos;
    }
    public int getYPos() {
        return yPos;
    }
    private void setYPos(int yPos) {
        this.yPos = yPos;
    }
    public int getId() {
        return id;
    }

    public void moveTowards(char direction) {
        switch (direction) {
            case 'W', 'w':
                setYPos(yPos - 1);
                break;
            case 'S', 's':
                setYPos(yPos + 1);
                break;
            case 'A', 'a':
                setXPos(xPos - 1);
                break;
            case 'D', 'd':
                setXPos(xPos + 1);
                break;
            default:
                break;
        }
    }
    public void moveTo(int xPos, int yPos) {
        setXPos(xPos);
        setYPos(yPos);
    }
}
