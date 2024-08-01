/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package beehive.rogueleague;

import javax.swing.JOptionPane;

/**
 *
 * @author BeeHive
 */
public class RogueLeague {
    
    public static void main(String[] args) {
        MapGrid test = MapGrid.create(20, "rift", 10);
        test.printGrid();
    }
    
}
