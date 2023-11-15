/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class DamageSkill extends RLSkill {

    public DamageSkill(String name, double multiplier, String scalling, int cost, int cooldown, int range) {
        super(name, multiplier, scalling, cost, cooldown, range);
    }
    
    @Override
    void calcSkill(RLChar caster, RLChar target) {
        if(caster.isEnemy(target)){
            double calc = caster.getStr() * getMultiplier();
            target.takeDMG(calc);
        }
    }

    @Override
    public String getDescription() {
        return "NYI";
    }
    
}
