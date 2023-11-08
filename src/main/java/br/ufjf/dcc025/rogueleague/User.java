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
public class User {
    //atributes
    private String userName;
    private String password;
    List<RLChar> personagens;

    private User(String userName, String password, List<RLChar> personagens) {
        this.userName = userName;
        this.password = password;
        this.personagens = personagens;
    }
    
    public User newUser(){
        //insert and check name
        String un = "default";
        //insert and confirm password
        String pw = "default";
        return new User(un,pw,new ArrayList<>());
    }
    
    
}
/*
Subclasses{
    Admin;
    Player/User;
}
Create/Edit/Delete{
    Admin(can freely c/e/d Users);
    User(can only c/e/d itself);
}
Owns{
Atributes:
    ID;
    Password;
    History;
Classes:
    Characters;
}
Methods{
    Create;
    Edit;
    Remove;
    Show;
    Play;
}
*/