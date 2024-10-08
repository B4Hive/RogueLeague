package beehive.rogueleague;

import java.util.List;

public class Entity {
    private int xPos;
    private int yPos;
    private final int id;
    private List <Effect> effects;

    public Entity(int xPos, int yPos, int id) {
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
    public List <Effect> getEffects() {
        return effects;
    }
    public void setEffect(Effect effect) {
        this.effects.add(effect);
    }
    public String tickEffects(){
        StringBuilder result = new StringBuilder("Effects Executed:\n");
        for(Effect effect : effects){
            result.append(effect.execute()).append("\n");
            effect.tickDuration();
            if(effect.getDuration() <= 0){
                effects.remove(effect);
            }
        }
        return result.toString();
    }
    private void checkCollisionAndMove(int x, int y) {
        if(CharacterLocator.getMapCollision(x, y) || CharacterLocator.getEntityCollision(x, y) != null)
            return;
        setXPos(x);
        setYPos(y);
    }
    public void moveTowards(char direction) {
        switch (direction) {
            case 'W', 'w':
                checkCollisionAndMove(xPos, yPos-1);
                break;
            case 'S', 's':
                checkCollisionAndMove(xPos, yPos + 1);
                break;
            case 'A', 'a':
                checkCollisionAndMove(xPos - 1, yPos);
                break;
            case 'D', 'd':
                checkCollisionAndMove(xPos + 1, yPos);
                break;
            default:
                break;
        }
    }
    public void moveTo(int xPos, int yPos) {
        setXPos(xPos);
        setYPos(yPos);
    }
    public void attack(Ability ability, Entity target) {
        //to attack in case of collision
    }
}
