/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class Entity {
    //atributes
    private int x;
    private int y;
    private final int id;
    /*
    private int hp;
    private int mp;
    private int xp;
    private int lvl;
    private final int str;
    private final int res;
    private final int ran;
    */
    //constructor
    public Entity(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        /*
        this.str = 1;
        this.res = 1;
        this.ran = 1;
        this.xp = 0;
        this.lvl = 1;
        this.hp = res * 100;
        this.mp = lvl * 100;
        */
    }
    //getters

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
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