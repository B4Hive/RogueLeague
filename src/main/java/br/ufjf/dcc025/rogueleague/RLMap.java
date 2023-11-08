/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public abstract class RLMap {
    /*
    Missing the map status part
    Those missing parts rely on Entities and RLSkill to be done
    */
    //atributes
    protected final int size;
    protected final int idGrid[][];
    protected int timer;
    protected List<RLChar> entities;
    
    protected RLChar target;
    //statusGrid[size][size] = {0} || {status id} ou
    //Map<String,Integer> status; //String = "x,y"; Integer = statusID;
    
    //constructor
    public RLMap(int size){
        timer = 0;
        entities = new ArrayList<>();
        
        this.size = size;
        idGrid = new int[size][size];
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                idGrid[x][y] = 1;
            }
        }
    }
    
    //methods
    protected abstract void addEntity();
    
    public RLChar getPC(){
        return entities.get(0);
    }
    
    public void move(RLChar e, char direction){
        if(!e.getState() || direction == ' ')
            return;
        int x = e.getX();
        int y = e.getY();
        switch(direction){
            case 'w' -> y--;
            case 's' -> y++;
            case 'a' -> x--;
            case 'd' -> x++;
        }
        if(!checkColision(e,x,y)){
            e.setX(x);
            e.setY(y);
        }
    }
    
    private boolean checkColision(RLChar e, int x, int y){
        for(RLChar other : entities){
            if(other.getX() == x && other.getY() == y && other.getState()){
                e.castSkill(0, other);
                return true;
            }
        }
        return (this.idGrid[x][y] != 0);
    }
    
    public void updateMap(){
        for(RLChar npc : entities){
            npc.updateEffect();
            if(timer%5 == 0)
                npc.regenMP();
            if(npc != getPC() && npc.getState()){
                int distX = Math.abs(npc.getX() - getPC().getX());
                int distY = Math.abs(npc.getY() - getPC().getY());
                if(distX <= 5 && distY <= 5){
                    if(distX > distY){
                        if(npc.getX() > getPC().getX())
                            this.move(npc,'a');
                        else
                            this.move(npc, 'd');
                    }
                    else{
                        if(npc.getY() > getPC().getY())
                            this.move(npc, 'w');
                        else
                            this.move(npc,'s');
                    }
                }
                else{
                    switch((int)(1+Math.random()*4)){
                        case 1 -> this.move(npc,'w');
                        case 2 -> this.move(npc,'a');
                        case 3 -> this.move(npc,'s');
                        case 4 -> this.move(npc,'d');
                    }
                }
            } else {
            }
        }
        timer++;
    }
    
    @Override
    public String toString() {
        String string = "Enemy Status\n";
        if(target != null)
            string += target.toString() + "\n";
        else
            string += "\n\n\n\n";
        String aimIn;
        String aimOut;
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                aimIn = " ";
                aimOut = " ";
                int printed = idGrid[x][y];
                for(RLChar e : entities){
                    if(e.getX() == x && e.getY() == y && e.getState()){
                        printed = e.getId();
                        if(e == target){
                            aimIn = "[";
                            aimOut = "]";
                        }
                    }
                }
                string += aimIn + RLData.mapIdBank.get(printed) + aimOut;
            }
            string += "\n";
        }
        string += "Timer: " + timer + "\n";
        string += "\nYour Status\n" + entities.get(0).toString();
        return string;
    }
    
    public void aimSkill(){
        for(RLChar e : entities){
            int distX = Math.abs(getPC().getX() - e.getX());
            int distY = Math.abs(getPC().getY() - e.getY());
            if(distX <=5 && distY <=5 && e!= getPC()){
                if(target == null){
                    target = e;
                    break;
                }
                else if(entities.indexOf(target) < entities.indexOf(e)){
                    target = e;
                    break;
                }
                else if(entities.indexOf(e) == entities.size()-1){
                    target = null;
                    break;
                }
            }
        }
    }
    
}

/*
Subclasses{
    LeagueMap;
    RogueMap;
    Personalized;
}
Create/Edit/Delete{
    Admin(can do them all);
    User(can create Personalized only);
}
Owns{
Classes:
    RLChar;
    Status;
}
Methods{
    Move RLChar;
    Update Status;
    Add/Remove RLChar;
    Print;
}

Screen Model:

*/