/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.util.Arrays;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class EffectSkill extends RLSkill {
    
    public EffectSkill(String name, double multiplier, String scalling, int cost, int cooldown, int range) {
        super(name, multiplier, scalling, cost, cooldown, range);
    }
    
    @Override
    void calcSkill(RLChar caster, RLChar target) {
        double e[] = new double[3];
        e[0] = RLData.effectBank.get(getScalling());
        e[1] = getMultiplier();
        e[2] = getCooldown();
        double stat = 0;
        if(e[0] == RLData.effectBank.get("DOT") || e[0] == RLData.effectBank.get("STR")) //DOT || STR
            stat = caster.getStr();
        else if(e[0] == RLData.effectBank.get("HOT") || e[0] == RLData.effectBank.get("RES")) //HOT || RES
            stat = caster.getRes();
        else if(e[0] == RLData.effectBank.get("DEX")) //DEX
            stat = caster.getDex();
        stat = stat * e[1];
        e[1] = stat;
        target.addEffect(e);
    }

    @Override
    public String getDescription() {
        return "NYI";
    }
    
}
