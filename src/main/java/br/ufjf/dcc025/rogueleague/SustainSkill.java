/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class SustainSkill extends RLSkill {
    private SustainSkill(String description, double multiplier, double cost, int cooldown, int range) {
        super(description, multiplier, cost, cooldown, range);
    }
    
    public static SustainSkill create(double multiplier, double cost, int cooldown, int range) {
        String description = "Heals " + multiplier + "% of RES as HP";
        description += "\nCost: " + cost + " MP";
        description += "\nCooldown: " + cooldown + " turns";
        description += "\nRange: " + range;
        return new SustainSkill(description, multiplier, cost, cooldown, range);
    }
    
    @Override
    void calcSkill(RLChar caster, RLChar target) {
        double calc = (caster.getRes() * multiplier/100);
        target.heal(calc);
    }
    
}
