import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
            ArrayList<ClassStorage.Player> players = new ArrayList<ClassStorage.Player>();
            ArrayList<ClassStorage.Monster> monsters = new ArrayList<ClassStorage.Monster>();
            players.add(new ClassStorage.Player("Max", 100, 20, 20, 5, 25));
            for(int i = 0; i < 3; i++) {
                monsters.add(new ClassStorage.Monster("Goblin", 10, 3, 3, 1, 10));
                monsters.add(new ClassStorage.Monster("Ork", 70, 10, 10, 15, 25));
            }
            System.out.println("Welcome to the arena to see the fight between the hero and monsters. Today our fighter " + players.get(0).get_name() + " is facing 3 goblins and 3 orks. Lets see who will win!");
            System.out.println("Your current parameters:" +
            "\nHealth: " + players.get(0).get_current_health() + 
            "\nAttack: " + players.get(0).get_attack() +
            "\nDefence: " + players.get(0).get_defence() +
            "\nDamage: " + players.get(0).get_min_damage() + " - " + players.get(0).get_max_damage());
    
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("Your current health: " + players.get(0).get_current_health());
                System.out.println("Choose the action and print it's number:");
                int i = 1;
                for(ClassStorage.Monster m : monsters) {
                    System.out.println(i + ". Attack " + m.get_name());
                    i++;
                }
                if(players.get(0).get_self_heal_count() < 4) System.out.println(i + ". Heal yourself up to 30% of HP \n");
                
                int choice = in.nextInt() - 1;
                if(choice > monsters.size() || (choice == monsters.size() && players.get(0).get_self_heal_count() >= 4)) 
                {
                    System.out.println("Incorrect option");
                    continue;
                }
                if(choice < monsters.size()) {
                    if(monsters.size() > 0) players.get(0).hit(monsters.get(choice));
                    if(monsters.get(choice).get_current_health() == 0) monsters.remove(choice);
                }
                else {
                    players.get(0).self_heal();
                }
    
                for(ClassStorage.Monster m : monsters) {
                    m.hit(players.get(0));
                    if(players.get(0).get_current_health() == 0) 
                    {
                        players.remove(0);
                        System.out.print("You've lost!");
                        break;
                    }
                }
            }
            while(players.isEmpty() != true && monsters.isEmpty() != true);
            in.close();
        }
}
