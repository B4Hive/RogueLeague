/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufjf.dcc025.rogueleague;

import java.io.IOException;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RogueLeague {
    
    public static void main(String[] args) throws IOException {
        RLData.initData();
        Screen teste = new Screen();
        teste.show();
        RLData.exportData();
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
        Character; (may be named as emotions for lore)
        Tower;
        Nexus;
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
GameModes{
    Deathmatch;
    Normal Game;
    Acumulação de XP?;
}
*/