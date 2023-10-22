/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author b4bru
 */
public class LeagueMap extends RLMap{
    
    //constructor
    public LeagueMap(int size) {
        super(size);
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(x != 0 && y != 0 && x != size-1 && y != size-1){
                    if( x > 0 && x < 4)
                        idGrid[x][y] = 0;
                    if( x < size-1 && x > size-5)
                        idGrid[x][y] = 0;
                    if( y > 0 && y < 4)
                        idGrid[x][y] = 0;
                    if( y < size-1 && y > size-5)
                        idGrid[x][y] = 0;
                    if( x >= y-2 && x <= y+2 )
                        idGrid[x][y] = 0;
                }
            }
        }
    }

    @Override
    public void addEntity() {
        if(entities.isEmpty())
            entities.add(new Entity(2,2,2));
        else if(entities.size() == 1)
            entities.add(new Entity(size-3,size-3,3));
    }
    
}