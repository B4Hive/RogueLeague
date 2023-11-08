/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class UltimateSkill extends RLSkill {
    //atributes
    RLSkill ultimate[];
    /*
    [0] = passive;
    [1-n] = active;
    */
    
    public UltimateSkill(String description, double multiplier, double cost, int cooldown, int range) {
        super(description, multiplier, cost, cooldown, range);
    }
    //
    public static UltimateSkill create(double multiplier, double cost, int cooldown, int range) {
        String description = "Deals " + multiplier + "% of STR as damage";
        description += "\nCost: " + cost + " MP";
        description += "\nCooldown: " + cooldown + " turns";
        description += "\nRange: " + range;
        return new UltimateSkill(description, multiplier, cost, cooldown, range);
    }
    @Override
    void calcSkill(RLChar caster, RLChar target) {
        
    }
    
}
