package beehive.rogueleague;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TempEngine {
    private static MapGrid map;
    private static Map<Integer, Entity> entities;
    private static char input;
    private static boolean active = false;

    public static void init(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the map size:");
        int size = scanner.nextInt();
        System.out.println("Please enter the map type (\"basic\", \"rift\", \"random\"):");
        String type = scanner.next();
        System.out.println("Please enter the map density (2, 30):");
        int density = scanner.nextInt();

        map = MapGrid.create(size, type, density);
        entities = new HashMap<>();

        System.out.println("How many entities you wish to spawn?");
        int amount = scanner.nextInt();
        if(amount < 1)
            amount = 1;
        for(int id = 0; id < amount; id++){
            int x = 0;
            int y = 0;
            while(map.getTile(x, y) != 0){
                x = (int) (Math.random() * map.getSize());
                y = (int) (Math.random() * map.getSize());
            }
            entities.put(id, new Entity(x, y, id));
        }
        CharacterLocator.init(map, entities);
        active = true;
        System.out.println("Map generated, Entities spawned");
    }

    public static void screen(){
        if(!active){
            System.out.println("Test Engine not started");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        //char opt;
        do{
            printScreen("Q = Exit; W, A, S, D = Move");
            String tempInput = scanner.next();
            input = tempInput.charAt(0);
            action();
        }while(input != 'q' && input != 'Q');
        System.out.println("\nTest finished");
    }

    private static void printScreen(String footnote){
        //int range = 2;
        for(int y = 0; y < map.getSize(); y++){
            for(int x = 0; x < map.getSize(); x++){
                char tile = '.';
                if(map.getTile(x, y) == 1)
                    tile = '#';
                for(int k: entities.keySet()){
                    if(entities.get(k).getXPos() == x && entities.get(k).getYPos() == y){
                        tile = '@';
                        if(entities.get(k).getId() != 0)
                            tile = '&';
                    }
                }
                System.out.print(" " + tile + " ");
            }
            System.out.println();
        }
        System.out.println(footnote);
        System.out.println();
    }

    private static void action(){
        switch(input){
            case 'q', 'Q':
                active = false;
                break;
            case 'w', 'W', 'a', 'A', 's', 'S', 'd', 'D':
                entities.get(0).moveTowards(input);
                break;
            default:
                break;
        }
    }
}
