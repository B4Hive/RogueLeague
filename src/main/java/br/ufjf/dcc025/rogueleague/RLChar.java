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
    private final int team;
    
    private boolean state;
    
    private final double hp[] = new double[2];
    private final int mp[] = new int[2];
    
    private int xp;
    private int lvl;
    
    private double str;
    private double res;
    private double dex;
    
    final RLSkill kit[];
    List<double[]> effects = new ArrayList<>();
    
    //constructors
    private RLChar(int x, int y, int id, int team, double str, double res, double dex, RLSkill[] kit) {
        this.x = x;
        this.y = y;
        
        this.id = id;
        this.team = team;
        
        this.state = true;
        
        this.xp = 0;
        this.lvl = 1;
        
        this.str = str;
        this.res = res;
        this.dex = dex;
        
        this.hp[0] = (res*10);
        this.hp[1] = this.hp[0];
        this.mp[0] = lvl * 10;
        this.mp[1] = this.mp[0];
        
        this.kit = kit;
    }
    
    public static RLChar createRandChar (int x, int y, int id, int team) {
        int strMod = 1;
	int resMod = 1;
	int dexMod = 1;
	while(strMod == resMod && resMod == dexMod){
            for(int i=0; i<3; i++){
                double rand = Math.random();
                if(rand < 0.34)
                    strMod++;
                else if(rand > 0.33 && rand < 0.67)
                    resMod++;
                else if(rand > 0.66 && rand < 1)
                    dexMod++;
            }
            if(strMod == resMod && resMod == dexMod){
                strMod = 1;
                resMod = 1;
                dexMod = 1;
            }
        }
        int str = strMod;
        int res = resMod;
        int dex = dexMod;
        
        //getKit(int str, int res, int dex)
        RLSkill kit[] = new RLSkill[4];
        kit[0] = RLData.skillBank.get("BasicAttack");
        kit[1] = RLData.skillBank.get("StrongAttack");
        kit[2] = RLData.skillBank.get("DOT");
        kit[3] = RLData.skillBank.get("BasicHeal");
        RLChar temp = new RLChar(x, y, id, team, str, res, dex, kit);
        System.out.print(temp.toString());
        return temp;
    }
    
    public static RLChar importChar (int x, int y, int team, String info) {
        //id=2, str=3.0, res=2.0, dex=1.0, kit=BasicAttack/Strong M.Hit/DOT/BasicHeal
        int id = 0;
        double str = 0;
        double res = 0;
        double dex = 0;
        String k[] = null;
        String attributes[] = info.split(", ");
        for(String a : attributes){
            String temp[] = a.split("=");
            switch(temp[0]){
                case "id" -> {
                    id = Integer.parseInt(temp[1]);
                }
                case "str" -> {
                    str = Double.parseDouble(temp[1]);
                }
                case "res" -> {
                    res = Double.parseDouble(temp[1]);
                }
                case "dex" -> {
                    dex = Double.parseDouble(temp[1]);
                }
                case "kit" -> {
                    k = temp[1].split("/");
                }
            }
        }
        //getKit(int str, int res, int dex)
        RLSkill kit[] = new RLSkill[k.length];
        for(int i = 0; i < kit.length; i++){
            kit[i] = RLData.skillBank.get(k[i]);
        }
        return new RLChar(x, y, id, team, str, res, dex, kit);
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
    public boolean isEnemy(RLChar other) {
        return (this.team != other.team);
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
        if(this.mp[0] >= this.kit[i].getCost() && !getCooldown(i)){
            this.mp[0] -= this.kit[i].getCost();
            this.kit[i].calcSkill(this, e);
            this.startCooldown(i);
        }
    }
    
    void regenMP(){
        if(this.mp[0] < this.mp[1]){
            this.mp[0]++;
        }
    }
    
    void addEffect(double effect[]){
        double e[] = new double[3];
        e[0] = effect[0]; //effect id
        e[1] = effect[1]; //effect multiplier
        e[2] = effect[2] + 1; // effect getCooldown
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
    
    private void startCooldown(int i){
        double cdEffect[] = new double[3];
        cdEffect[0] = RLData.effectBank.get("CD");
        cdEffect[1] = i;
        cdEffect[2] = this.kit[i].getCooldown();
        this.addEffect(cdEffect);
    }
    boolean getCooldown(int i){
        for(double[] e : effects){
            if(e[0] == RLData.effectBank.get("CD") && e[1] == i)
                return true;
        }
        return false;
    }
    
    public String getStatBar(){
        String rlchar = "";
        //hp bar
        int hpPercent = (int) (hp[0]/hp[1]*10);
        String hpBar = "HP = 0[";
        for(int i = 0; i<10; i++){
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
    public String getKitBar(){
        String k = "Skills\n";
        for(int i=0; i < kit.length; i++){
            k += "[" + String.valueOf(i);
            k += "] " + kit[i].toString();
            for(double[] e : effects){
                if(e[0] == RLData.effectBank.get("CD") && e[1] == i){
                    k += " CD: " + e[2];
                }
            }
            k += "\n";
        }
        return k;
    }
    
    public String listEffects(){
        String e = "Effects: ";
        for(double[] ef : effects){
            for(String key : RLData.effectBank.keySet()){
                if(RLData.effectBank.get(key) == ef[0] && !key.equals("CD")){
                    e += key + ", " + String.valueOf(ef[1]) + ", " + String.valueOf(ef[2]) + "\n";
                    break;
                }
            }
        }
        return e;
    }
    
    private String kitExport(){
        String k = "";
        for(RLSkill s : kit){
            k += s.getName() + "/";
        }
        k = k.substring(0, k.length()-1);
        return k;
    }
    
    @Override
    public String toString() {
        return "RLChar:" + "id=" + id + ", str=" + str + ", res=" + res + ", dex=" + dex + ", kit=" + kitExport() + ";\n";
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