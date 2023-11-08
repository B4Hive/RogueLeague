/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RLChar {
    //atributes
    private int x;
    private int y;
    private final int id;
    
    private boolean state;
    private int team;
    
    private double hp[] = new double[2];
    private int mp[] = new int[2];
    private int xp;
    private int lvl;
    private double str;
    private double res;
    private double dex;
    
    final RLSkill kit[] = new RLSkill[5];
    List<double[]> effects;
    
    //constructors
    public RLChar(int x, int y, int id, int team) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.state = true;
        this.team = team;
        int strMod = 1;
	int resMod = 1;
	int dexMod = 1;
	while(strMod == resMod && resMod == dexMod){
            strMod = 1;
            resMod = 1;
            dexMod = 1;
            for(int i=0; i<3; i++){
                double rand = Math.random();
                if(rand < 0.34)
                    strMod++;
                else if(rand > 0.33 && rand < 0.67)
                    resMod++;
                else if(rand > 0.66 && rand < 1)
                    dexMod++;
            }
        }
        this.str = strMod;
        this.res = resMod;
        this.dex = dexMod;
        this.xp = 0;
        this.lvl = 1;
        
        this.hp[0] = (res*10);
        this.hp[1] = this.hp[0];
        this.mp[0] = lvl * 10;
        this.mp[1] = this.mp[0];
        /*
        kit[0] = basic attack
        kit[1-3] = skill
        kit[4] = basic heal
        kit[5] = passive/ulti (the passive is active only while the ulti isn't charged)
        */
        kit[0] = RLData.strSkillBank.get("ba");
        kit[1] = RLData.strSkillBank.get("sm1");
        kit[2] = RLData.strSkillBank.get("be");
        kit[3] = RLData.resSkillBank.get("be");
        kit[4] = RLData.resSkillBank.get("bh");
        //kit[5] = RLData.skillBank.get("ulti");
        
        effects = new ArrayList<>();
    }
    
    //getters & setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public boolean getState(){
        return state;
    }
    public double getHp() {
        return hp[0];
    }
    public int getMp() {
        return mp[0];
    }
    public int getXp() {
        return xp;
    }
    public int getLvl() {
        return lvl;
    }
    public double getStr() {
        return str;
    }
    public double getRes() {
        return res;
    }
    public double getDex() {
        return dex;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    private void altState(){
        if(getState() == true){
            this.state = false;
            setX(-5);
            setY(-5);
        }
        else{
            this.state = true;
        }
            
    }
    
    //methods
    void takeDMG(double damage){
        double calc = damage-((double)res/10*lvl);
        if(calc > 0)
            this.hp[0] -= calc;
        else
            this.hp[0]--;
        if(this.hp[0] <= 0){
            altState();
            this.hp[0] = 0;
        }
    }
    void heal(double healing){
        double calc = healing;
        if(this.hp[0] + calc <= this.hp[1])
            this.hp[0] += calc;
        else
            this.hp[0] = this.hp[1];
    }
    void castSkill(int i, RLChar e){
        if(e==null)
            return;
        if(this.mp[0] >= this.kit[i].cost){
            this.mp[0] -= this.kit[i].cost;
            this.kit[i].calcSkill(this, e);
        }
    }
    void regenMP(){
        if(this.mp[0] < this.mp[1]){
            this.mp[0]++;
        }
    }
    void addEffect(double effect[]){
        double e[] = new double[3];
        e[0] = effect[0];
        e[1] = effect[1];
        e[2] = effect[2] + 1;
        if(e[0] == RLData.effectBank.get("STR"))
            this.str += e[1];
        else if(e[0] == RLData.effectBank.get("RES"))
            this.res += e[1];
        else if(e[0] == RLData.effectBank.get("DEX"))
            this.dex += e[1];
        effects.add(e);
    }
    void removeEffect(double e[]){
        if(e[0] == RLData.effectBank.get("STR"))
            this.str -= e[1];
        else if(e[0] == RLData.effectBank.get("RES"))
            this.res -= e[1];
        else if(e[0] == RLData.effectBank.get("DEX"))
            this.dex -= e[1];
        effects.remove(e);
    }
    void updateEffect(){
        int i = 0;
        while (i < effects.size()) {
            if(effects.get(i)[0] == RLData.effectBank.get("DOT"))
                this.takeDMG(effects.get(i)[1]);
            else if(effects.get(i)[0] == RLData.effectBank.get("HOT"))
                this.heal(effects.get(i)[1]);
            effects.get(i)[2] = effects.get(i)[2] - 1;
            if(effects.get(i)[2] == 0)
                removeEffect(effects.get(i));
            else
                i++;
        }
    }
    @Override
    public String toString() {
        String rlchar = "";
        //hp bar
        int hpPercent = (int) (hp[0]/hp[1]*60);
        String hpBar = "HP = 0[";
        for(int i = 0; i<60; i++){
            if(i<hpPercent)
                hpBar += "#";
            else
                hpBar += "-";
        }
        hpBar += "]" + this.hp[1];
        rlchar += hpBar + "\n";
        //mp bar
        rlchar += String.format("MP = [%3d/%3d]\n", mp[0] , mp[1]);
        //stats
        String stats = "XP=" + xp + ", LVL=" + lvl + ", STR=" + str + ", RES=" + res + ", DEX=" + dex + "\n";
        rlchar += stats;
        return rlchar;
    }
}
/*
Subclasses{
    Character;
    Towers;
}
Create/Edit/Delete{
    Admin(able to do it for every existing character);
    User(able to do it for owned characters only);
}
Owns{
Atributes:
    HP; (super)
    MP; (sub)
    XP; (sub)
    LvL; (super)
    Strenght; (super)
    Resistence; (super)
    Range; (super)
    Position; (super)
Classes:
    Status;
    Skills;
Methods:
    Damage; (take/deal/heal/shield)
    Update; (update status/cooldowns/durations)
    Move; (map)
    Modify; (level up, apply/remove status)
}
*/