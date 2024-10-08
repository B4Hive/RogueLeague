package beehive.rogueleague;

public class Ability {
    private final float actionValue;
    private final int cooldownMax;
    private int cooldown;
    private final Effect effect;

    //this method will become private when I have the patience to make a way of confirming it's a viable ability
    public Ability(int actionValue, int cooldownMax, Effect effect) {
        this.actionValue = actionValue;
        this.cooldownMax = cooldownMax;
        this.cooldown = 0;
        this.effect = effect;
    }
    public float getActionValue() {
        return actionValue;
    }
    public int getCooldown() {
        return cooldown;
    }
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    public void tickCooldown() {
        if(cooldown > 0) cooldown--;
    }
    public String getEffect() {
        return effect.toString();
    }
    public void cast(Entity caster, Entity target){
        if(cooldown > 0)
            return;
        cooldown = cooldownMax;
        int tempValue = (int) actionValue; //will read the caster stats to fill tempValue since AV is just a multiplier
        effect.apply(tempValue, target);
    }
}
