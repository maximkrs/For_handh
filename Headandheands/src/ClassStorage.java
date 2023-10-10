public class ClassStorage {

    public static class Creature {
    protected int attack;
    protected int defence;
    protected int min_damage;
    protected int max_damage;
    protected int max_health;
    protected int current_health;
    protected String name;

    public void get_hit(int damage_hitten, Creature hitter) {
        if(increase_current_health(damage_hitten * -1) <= 0) death(hitter);
    }

    public void death(Creature killer) {
        System.out.println(this.name + " is killed by " + killer.get_name());
    }

    public void hit(Creature target) {
        System.out.println("Hitting " + target.get_name());
        int attack_modifier = this.attack - target.get_defence() + 1;
        int diceroll_count = 0;
        boolean diceroll_success = false;
        do {
            int temp = (int)(Math.random()*5 + 1);
            if(temp >= 5) {
                diceroll_success = true;
            }
            diceroll_count++;
        }
        while(diceroll_count <= attack_modifier);
        if(diceroll_success) {
            int damage = (int)(Math.random()*(this.max_damage - this.min_damage) + this.min_damage);
            System.out.println(this.name + " deals " + damage + " damage to " + target.get_name());
            target.get_hit(damage, this);
        }
        else System.out.println("Attack failed");
        System.out.println();
    }

    public int set_current_health(int new_health)
    {
        if(new_health < 0) return -1;
        if(new_health > this.max_health) return -2;
        this.current_health = new_health;
        return this.current_health;
    }

    public int increase_current_health(int adding_health)
    {
        if(this.current_health + adding_health < 0) 
        {
            this.current_health = 0;
            return this.current_health;
        }
        if(this.current_health + adding_health > this.max_health) 
        {
            this.current_health = this.max_health;
            return this.current_health;
        }
        this.current_health += adding_health;
        return this.current_health;
    }

    public int set_max_health(int new_health)
    {
        if(new_health < 0) return -1;
        this.max_health = new_health;
        return this.max_health;
    }

    public int increase_max_healt(int adding_health)
    {
        if(this.current_health + adding_health < 0) return -1;
        this.max_health += adding_health;
        return this.max_health;
    }

    public int set_attack(int new_attack)
    {
        if(new_attack < 1 || new_attack > 30) return -1;
        this.attack = new_attack;
        return this.attack;
    }

    public int increase_attack(int adding_attack)
    {
        if(this.attack + adding_attack < 1 || this.attack + adding_attack > 30) return -1;
        this.attack += adding_attack;
        return this.attack;
    }

    public int set_defence(int new_defence)
    {
        if(new_defence < 1 || new_defence > 30) return -1;
        this.defence = new_defence;
        return this.defence;
    }

    public int increase_defence(int adding_defence)
    {
        if(this.attack + adding_defence < 1 || this.attack + adding_defence > 30) return -1;
        this.defence += adding_defence;
        return this.defence;
    }

    public void set_damage(int new_min_damage, int new_max_damage)
    {
        if(new_min_damage > new_max_damage) {
            this.min_damage = new_max_damage;
            this.max_damage = new_min_damage;
        }
        else {
            this.min_damage = new_min_damage;
            this.max_damage = new_max_damage;
        }
    }

    public int get_max_health() {
        return this.max_health;
    }

    public int get_current_health() {
        return this.current_health;
    }

    public int get_attack() {
        return this.attack;
    }

    public int get_defence() {
        return this.defence;
    }

    public int get_min_damage() {
        return this.min_damage;
    }

    public int get_max_damage() {
        return this.max_damage;
    }

    public String get_name() {
        return this.name;
    }
}

public static class Player extends Creature {

    protected int self_heal_count;

    public Player(String name, int health, int attack, int defence, int min_damage, int max_damage)
    {
        this.name = name;
        this.set_max_health(health);
        this.set_current_health(health);
        this.set_attack(attack);
        this.set_defence(defence);
        this.set_damage(min_damage, max_damage);
        this.self_heal_count = 0;
    }

    public Player(String name, int max_health, int current_health, int attack, int defence, int min_damage, int max_damage)
    {
        this.name = name;
        this.set_max_health(max_health);
        this.set_current_health(current_health);
        this.set_attack(attack);
        this.set_defence(defence);
        this.set_damage(min_damage, max_damage);
        this.self_heal_count = 0;
    }

    public int self_heal()
    {
        if(this.self_heal_count >= 4)
        {
            System.out.println("You can't heal yourself anymore");
            return -1;
        }
        this.self_heal_count++;
        int healing_health = (int) (0.3 * this.max_health);
        return this.increase_current_health(healing_health);
    }

    public int get_self_heal_count()
    {
        return this.self_heal_count;
    }
}

public static class Monster extends Creature {
    public Monster(String name, int health, int attack, int defence, int min_damage, int max_damage)
    {
        this.name = name;
        this.set_max_health(health);
        this.set_current_health(health);
        this.set_attack(attack);
        this.set_defence(defence);
        this.set_damage(min_damage, max_damage);
    }

    public Monster(String name, int max_health, int current_health, int attack, int defence, int min_damage, int max_damage)
    {
        this.name = name;
        this.set_max_health(max_health);
        this.set_current_health(current_health);
        this.set_attack(attack);
        this.set_defence(defence);
        this.set_damage(min_damage, max_damage);
    }
}
}