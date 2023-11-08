/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RLData {
    public static Map<Integer,Character> mapIdBank = new HashMap<>();
    public static Map<String,Double> effectBank = new HashMap<>();
    public static Map<String,RLSkill> strSkillBank = new HashMap<>();
    public static Map<String,RLSkill> resSkillBank = new HashMap<>();
    public static Map<String,RLSkill> dexSkillBank = new HashMap<>();
    
    public RLData() {
        
    }
    
    public static void initData(){
        initMapID();
        initEffects();
        initSkills();
    }
    
    public static void initEffects(){
        effectBank.put("DOT", 0.041520);
        effectBank.put("HOT", 0.081520);
        effectBank.put("STR", 0.192018);
        effectBank.put("DEX", 0.040524);
        effectBank.put("RES", 0.180519);
        
    }
    
    private static void initMapID() {
        mapIdBank.put(0, '.');
        mapIdBank.put(1,'#');
        mapIdBank.put(2,'@');
        mapIdBank.put(3,'&');
        mapIdBank.put(4,'T');
        mapIdBank.put(5,'A');
        mapIdBank.put(6,'E');
        
    }
    
    private static void initSTRSkill(){
        //Basic Attack
        RLData.strSkillBank.put("ba", DamageSkill.create(100, 0, 0, 1));
        
        //Basic Effect
        RLData.strSkillBank.put("be", EffectSkill.create("STR", 50, 2, 2, 2, 0));
        
        //Other Skills
        RLData.strSkillBank.put("sm1", DamageSkill.create(150, 4, 3, 2));
    }
    
    private static void initRESSkill(){
        //Basic Heal
        RLData.resSkillBank.put("bh", SustainSkill.create(100, 1, 0, 0));
        
        //Basic Effect
        RLData.resSkillBank.put("be", EffectSkill.create("RES", 50, 2, 2, 2, 0));
    }
    
    private static void initDEXSkill(){
        //Basic Effect
        RLData.resSkillBank.put("be", EffectSkill.create("DEX", 50, 2, 2, 2, 0));
        
    }
    
    private static void initSkills(){
        initSTRSkill();
        initRESSkill();
        initDEXSkill();
    }
}
