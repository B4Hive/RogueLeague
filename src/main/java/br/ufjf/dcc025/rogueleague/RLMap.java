/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public abstract class RLMap {
    /*
    Missing the map status and the update parts
    Those missing parts rely on Entities and Skill to be done
    */
    //atributes
    protected final int size;
    protected final int idGrid[][];
    protected List<Entity> entities;
    protected int timer;
    protected Map<Integer,Character> idToChar;
    
    //constructor
    public RLMap(int size){
        timer = 0;
        
        idToChar = new HashMap<>();
        idToChar.put(0, '.');
        idToChar.put(1,'#');
        idToChar.put(2,'@');
        idToChar.put(3,'&');
        idToChar.put(4,'T');
        idToChar.put(5,'A');
        idToChar.put(6,'E');
        
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
    public abstract void addEntity();
    
    public void move(Entity e, char direction){
        switch(direction){
            case 'w' -> {
                if(idGrid[e.getX()][e.getY()-1] == 0)
                    e.setY(e.getY()-1);
            }
            case 's' -> {
                if(idGrid[e.getX()][e.getY()+1] == 0)
                    e.setY(e.getY()+1);
            }
            case 'a' -> {
                if(idGrid[e.getX()-1][e.getY()] == 0)
                    e.setX(e.getX()-1);
            }
            case 'd' -> {
                if(idGrid[e.getX()+1][e.getY()] == 0)
                    e.setX(e.getX()+1);
            }
            default -> {
            }
        }
        timer++;
    }
    
    public void autoMove(Entity pc, Entity target){
        int distX = Math.abs(pc.getX() - target.getX());
        int distY = Math.abs(pc.getY() - target.getY());
        while(distX > 1 && distY >1){
            distX = Math.abs(pc.getX() - target.getX());
            distY = Math.abs(pc.getY() - target.getY());
            //exceptions needed here for colision with walls
            if( distX > distY){
                if(pc.getX() < target.getX())
                    move(pc,'d');
                else if(pc.getX() > target.getX())
                    move(pc,'a');
            }
            else{
                if(pc.getY() > target.getY())
                    move(pc,'w');
                if(pc.getY() < target.getY())
                    move(pc,'s');
            }
        }
    }

    @Override
    public String toString() {
        String string = "";
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                int printed = idGrid[x][y];
                for(Entity e : entities){
                    if(e.getX() == x && e.getY() == y)
                        printed = e.getId();
                }
                string += " " + idToChar.get(printed) + " ";
                //sub these spaces for status whenever entities are done
            }
            string += "\n";
        }
        string += "Timer: " + timer;
        string += "\n";
        return string;
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
    Entity;
    Status;
}
Methods{
    Move Entity;
    Update Status;
    Add/Remove Entity;
    Print;
}
*/