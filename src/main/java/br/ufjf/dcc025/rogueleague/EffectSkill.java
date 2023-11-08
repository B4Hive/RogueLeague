/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class EffectSkill extends RLSkill {
    
    private final double effect[] = new double[3];
    
    private EffectSkill(String description, double multiplier, double cost, int cooldown, int range) {
        super(description, multiplier, cost, cooldown, range);
    }
    
    public static EffectSkill create(String stat, double multiplier, double cost, int cooldown, int duration, int range) {
        String description = "";
        double e[] = {0,0,0};
        switch(stat){
            case "DOT" -> {
                description = "Deals " + multiplier + "% of STR as " + stat;
                e[0] = RLData.effectBank.get(stat);
            }
            case "HOT" -> {
                description = "Heals " + multiplier + "% of RES as " + stat;
                e[0] = RLData.effectBank.get(stat);
            }
            case "STR" -> {
                description = "Changes " + stat + " in " + multiplier + "%";
                e[0] = RLData.effectBank.get(stat);
            }
            case "DEX" -> {
                description = "Changes " + stat + " in " + multiplier + "%";
                e[0] = RLData.effectBank.get(stat);
            }
            case "RES" -> {
                description = "Changes " + stat + " in " + multiplier + "%";
                e[0] = RLData.effectBank.get(stat);
            }
        }
        e[1] = multiplier;
        description += "\nCost: " + cost + " MP";
        description += "\nCooldown: " + cooldown + " turns";
        description += "\nDuration: " + duration + " turns";
        description += "\nRange: " + range;
        e[2] = duration;
        EffectSkill temp = new EffectSkill(description, multiplier, cost, cooldown, range);
        setEffect(temp, e);
        return temp;
    }
    
    static void setEffect(EffectSkill s, double e[]){
        s.effect[0] = e[0];
        s.effect[1] = e[1];
        s.effect[2] = e[2];
    }
    @Override
    void calcSkill(RLChar caster, RLChar target) {
        //tratar o status primeiro pq por enquanto tá com o multiplicador puro e precisa ser o valor
        double e[] = new double[3];
        e[0] = effect[0];
        e[1] = effect[1];
        e[2] = effect[2];
        double stat = 0;
        if(effect[0] == RLData.effectBank.get("DOT") || effect[0] == RLData.effectBank.get("STR")) //DOT || STR
            stat = caster.getStr();
        else if(effect[0] == RLData.effectBank.get("HOT") || effect[0] == RLData.effectBank.get("RES")) //HOT || RES
            stat = caster.getRes();
        else if(effect[0] == RLData.effectBank.get("DEX")) //DEX
            stat = caster.getDex();
        stat *= e[1]/100;
        e[1] = stat;
        target.addEffect(e);
    }
}
