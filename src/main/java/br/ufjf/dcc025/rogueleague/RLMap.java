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
public abstract class RLMap {
    /*
    Missing the map status part
    Those missing parts rely on Entities and RLSkill to be done
    */
    //atributes
    protected final int size;
    protected final int idGrid[][];
    protected int timer;
    protected List<RLChar> characters;
    
    protected RLChar target;
    //statusGrid[size][size] = {0} || {status id} ou
    //Map<String,Integer> status; //String = "x,y"; Integer = statusID;
    
    //constructor
    public RLMap(int size){
        timer = 0;
        characters = new ArrayList<>();
        
        this.size = size;
        idGrid = new int[size][size];
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                idGrid[x][y] = 1;
            }
        }
    }
    
    //methods
    protected abstract void addEntity(int team);
    
    public RLChar getPC(){
        return characters.get(0);
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
        for(RLChar other : characters){
            if(other.getX() == x && other.getY() == y && other.getState()){
                e.castSkill(0, other);
                return true;
            }
        }
        return (this.idGrid[x][y] != 0);
    }
    
    private void AI(RLChar npc){
        if(npc.getState() && npc != getPC()){
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
            }
    }
    
    public void updateMap(){
        timer++;
        for(RLChar c : characters){
            c.updateEffect();
            if(timer%5 == 0)
                c.regenMP();
            AI(c);
        }
    }
    
    public String printMap(int zoom){
        String string = "Enemy Status\n";
        if(target != null)
            string += target.getStatBar() + "\n";
        else
            string += "\n\n\n\n";
        String aimIn;
        String aimOut;
        int i = getPC().getX();
        int j = getPC().getY();
        if(i - zoom < 0)
            i = zoom;
        if(i + zoom >= size)
            i = size - zoom;
        if(j - zoom < 0)
            j = zoom;
        if(j + zoom >= size)
            j = size - zoom;
        
        for(int y = j - zoom; y < j + zoom; y++){
            for(int x = i - zoom; x < i + zoom; x++){
                aimIn = " ";
                aimOut = " ";
                int printed = idGrid[x][y];
                for(RLChar e : characters){
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
        string += "\nYour Status\n" + getPC().getStatBar();
        string += "\n" + getPC().getKitBar();
        string += "\n" + getPC().listEffects();
        
        return string;
    }
    
    public void aimSkill(){
        for(RLChar e : characters){
            int distX = Math.abs(getPC().getX() - e.getX());
            int distY = Math.abs(getPC().getY() - e.getY());
            if(distX <=5 && distY <=5 && e!= getPC()){
                if(target == null){
                    target = e;
                    break;
                }
                else if(characters.indexOf(target) < characters.indexOf(e)){
                    target = e;
                    break;
                }
                else if(characters.indexOf(e) == characters.size()-1){
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

*/