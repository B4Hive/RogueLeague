/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufjf.dcc025.rogueleague;

import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RogueLeague {
    
    
    
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Lucida Console", Font.PLAIN, 14));
        RLData.initData();
        Screen teste = new Screen();
        teste.show();
        //RLMap test = new LeagueMap(15);
        //test.tempScreen();
        
    }
}

/*
Classes:
    Map{
        LeagueMap;
        RogueMap;
        Personalized;
    }
    Entity{
        Character;
        Tower;
    }
    Skill{
        Sustain;
        Damage;
        Status;
    }
    User{
        Admin;
        Player;
    }
*/