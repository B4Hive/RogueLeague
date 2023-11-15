/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.rogueleague;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class RLData {
    public static Map<Integer,Character> mapIdBank = new HashMap<>();
    public static Map<String,Double> effectBank = new HashMap<>();
    public static Map<String,RLSkill> skillBank = new HashMap<>();
    public static Map<Integer,String> characterBank = new HashMap<>();
    
    public static void initData() throws FileNotFoundException{
        initMapID();
        initEffects();
        importData();
    }
    
    public static void initEffects(){
        effectBank.put("DOT", 0.041520);
        effectBank.put("HOT", 0.081520);
        effectBank.put("STR", 0.192018);
        effectBank.put("DEX", 0.040524);
        effectBank.put("RES", 0.180519);
        effectBank.put("CD", 0.0304);
        
    }
    
    private static void initMapID() {
        mapIdBank.put(0, '.');
        mapIdBank.put(1, '#');
        mapIdBank.put(101, '@');
        mapIdBank.put(102, '&');
        mapIdBank.put(103, 'T');
        mapIdBank.put(104, 'A');
        mapIdBank.put(105, 'E');
        
    }
    
    public static void exportData() throws IOException{
        FileWriter fOut = new FileWriter("RLData");
        String compiledData = "User:login=admin, password=admin;\n";
        //User data
        for(String key : skillBank.keySet()){
            compiledData += skillBank.get(key).toString() + "\n";
        }
        fOut.flush();
        fOut.write(compiledData);
        fOut.close();
    }
    
    private static void initSkill(String splitLine[]){
        String name = "";
        double multiplier = 0;
        String scalling = "";
        int cost = 0;
        int cooldown = 0;
        int range = 0;
        
        String splitAttributes[] = splitLine[1].split(", ");
        for(String s : splitAttributes){
            String temp[] = s.split("=");
            switch(temp[0]){
                case "name" -> {
                    name = temp[1];
                }
                case "multiplier" -> {
                    multiplier = Double.parseDouble(temp[1]);
                }
                case "scalling" -> {
                    scalling = temp[1];
                }
                case "cost" -> {
                    cost = Integer.parseInt(temp[1]);
                }
                case "cooldown" -> {
                    cooldown = Integer.parseInt(temp[1]);
                }
                case "range" -> {
                    range = Integer.parseInt(temp[1]);
                }
            }
        }
        switch(splitLine[0]){
            case "DamageSkill" -> {
                skillBank.put(name, new DamageSkill(name, multiplier, scalling, cost, cooldown, range));
            }
            case "SustainSkill" ->{
                skillBank.put(name, new SustainSkill(name, multiplier, scalling, cost, cooldown, range));
            }
            case "EffectSkill" -> {
                skillBank.put(name, new EffectSkill(name, multiplier, scalling, cost, cooldown, range));
            }
        }
    }
    
    private static void initChar(String splitLine[]){
        //id=2, str=3.0, res=2.0, dex=1.0, kit=BasicAttack/Strong M.Hit/DOT/BasicHeal
        String splitAttributes[] = splitLine[1].split(", ");
        String temp[] = splitAttributes[0].split("=");
        characterBank.put(Integer.valueOf(temp[1]), splitLine[1]);
    }
    
    private static void importData() throws FileNotFoundException{
        Scanner file = new Scanner(new FileReader("RLData")).useDelimiter(";\n");
        while(file.hasNext()){
            String line = file.next();
            String splitLine[] = line.split(":",2);
            switch(splitLine[0]){
                case "DamageSkill", "SustainSkill", "EffectSkill" -> {
                    initSkill(splitLine);
                }
                case "RLChar" ->{
                    initChar(splitLine);
                }
                case "User" -> {
                    
                }
            }
        }
    }
    
}

//tem que passar isso tudo pra FileWriter
//um arquivo pra cada coisa então:
/*
DamageSkillData.txt
SustainSkillData.txt
EffectSkillData.txt (inclui effects aqui também
CharacterData.txt
UserData.txt
MapData.txt

*/
