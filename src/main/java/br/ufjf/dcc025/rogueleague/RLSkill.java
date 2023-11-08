/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public abstract class RLSkill {
    //atributes
    protected final String description;
    protected final double multiplier;
    protected final double cost;
    protected final int cooldown;
    protected final int range; //cityblock distance(self = 0, melee = 1, ranged >= 2)

    //constructor
    public RLSkill(String description, double multiplier, double cost, int cooldown, int range) {
        this.description = description;
        this.multiplier = multiplier;
        this.cost = cost;
        this.cooldown = cooldown;
        this.range = range;
    }
    
    //getters
    public String getDescription() {    
        return description;
    }
    public double getMultiplier() {
        return multiplier;
    }
    public double getCost() {
        return cost;
    }
    public int getCooldown() {
        return cooldown;
    }
    public int getRange() {
        return range;
    }
    //methods
    abstract void calcSkill(RLChar caster, RLChar target);

    @Override
    public String toString() {
        return "Skill\n" + description;
    }
    
}
/*
Subclasses{
    Damage;
    Sustain;
    Effect;
    Movement; (talvez?)
}
Create/Edit/Delete{
    Admin(maybe);
    User(can't);
}
Owns{
Atributes:
    Multiplier;
    Cooldown;
    Duration;
    Range;
    Cost;
}
Methods{
    Cast;
    Update Cooldown;
}
Constraints{
    Each skill has a basic version;
    DamageSkill{
        Multiplier(100);
        Cooldown(0);
        Duration(0);
        Range(1);
        Cost(0);
    }
    SustainSkill{
        Multiplier(100);
        Cooldown(0);
        Duration(0);
        Range(0)
        Cost(1);
    }
    EffectSkill{
        Multiplier(50);
        Cooldown(2);
        Duration(2);
        Range(0);
        Cost(2);
    }
    Each skill starts with 100% multiplier and 1 point to distribute;
    All other Skill Stats start with (0);
    pointBehaviour{
        Multiplier + 10 -> point--;
        Multiplier - 10 -> point++;
        Cooldown + 1 -> point++;
        Cooldown - 1 -> point--;
        Duration + 1 -> point--;
        Duration - 1 -> point++;
        Range + 1 -> point--;
        Range - 1 -> point++;
        Cost + 1 -> point++;
        Cost - 1 -> point--;
    }
    Como Range não é uma variável existente (ainda) este vai ser o mais complicado de interagir;
}
*/