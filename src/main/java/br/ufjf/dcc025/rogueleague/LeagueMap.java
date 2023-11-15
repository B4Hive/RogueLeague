/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class LeagueMap extends RLMap{
    
    //constructor
    public LeagueMap(int size) {
        super(size);
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(x != 0 && y != 0 && x != size-1 && y != size-1){
                    if( x > 0 && x < 6)
                        idGrid[x][y] = 0;
                    if( x < size-1 && x > size-7)
                        idGrid[x][y] = 0;
                    if( y > 0 && y < 6)
                        idGrid[x][y] = 0;
                    if( y < size-1 && y > size-7)
                        idGrid[x][y] = 0;
                    if( x >= y-3 && x <= y+3 )
                        idGrid[x][y] = 0;
                }
            }
        }
        this.addEntity(0);
        this.addEntity(1);
    }

    @Override
    public void addEntity(int team) {
        switch(team){
            default -> {}
            case 0 -> characters.add(RLChar.importChar(3,3,team, "id=101, str=1.0, res=2.0, dex=3.0, kit=BasicShot/AimedShot/DOT/HOT"));
            case 1 -> characters.add(RLChar.createRandChar(size-4,size-4,102 , team));
        }   
    }
    
}