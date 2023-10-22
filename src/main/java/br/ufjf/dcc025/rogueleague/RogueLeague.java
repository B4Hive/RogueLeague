/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufjf.dcc025.rogueleague;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RogueLeague {

    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Lucida Console", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        RLMap test = new LeagueMap(15);
        JOptionPane.showMessageDialog(null, test.toString());
        test.addEntity();
        JOptionPane.showMessageDialog(null, test.toString());
        test.addEntity();
        JOptionPane.showMessageDialog(null, test.toString());
        test.autoMove(test.entities.get(0), test.entities.get(1));
        JOptionPane.showMessageDialog(null, test.toString());
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