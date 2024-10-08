package beehive.rogueleague;

import java.util.List;

public abstract class Effect {
    private int value;
    private final int effectID;
    private int duration;

    Effect(int effectID, int duration) {
        this.effectID = effectID;
        this.duration = duration;
    }
    public int getEffectID() {
        return effectID;
    }
    public int getDuration() {
        return duration;
    }
    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        this.value = value;
    }
    private void setDuration(int duration) {
        this.duration = duration;
    }
    public void tickDuration() {
        this.duration--;
    } //will be private and called inside execute
    public void apply(int value, Entity target){
        setValue(value);
        List<Effect> effects = target.getEffects();
        for(Effect effect : effects){
            if(effect.getEffectID() == effectID){
                effect.setDuration(duration);
                return;
            }
        }
        target.setEffect(this);
    }

    //I want tickDuration on execute to make it private
    public abstract String execute();
}
