package pokemoni;
public abstract class Pokemons {
    private String vards;
    private String trainerVards;
    private int hp;
    private int dmg;
    private int ultDmg;
    private int def;
    private boolean defended;
    private int superCharge;

    public Pokemons(String vards, String trainerVards, int hp, int dmg, int ultDmg, int def) {
        this.vards = vards;
        this.trainerVards = trainerVards;
        this.hp = hp;
        this.dmg = dmg;
        this.ultDmg = ultDmg;
        this.def = def;
        this.defended = false;
        this.superCharge = 0;
    }

    public String getVards() {return this.vards; }
    public String getTrainerVards() { return trainerVards; }
    public int getHp() { return hp; }
    public int getDmg() { return dmg; }
    public int getUltDmg() { return ultDmg; }
    public int getDef() { return def; }
    public boolean isDefended() { return defended; }
    public int getSuperCharge() { return superCharge; }

    public void setHp(int hp) { this.hp = hp; }
    public void setDmg(int dmg) { this.dmg = dmg; }
    public void setDef(int def) { this.def = def; }
    public void setDefended(boolean defended) { this.defended = defended; }
    public void setSuperCharge(int superCharge) { this.superCharge = superCharge; }

    public void attack(Pokemons target) {
        int damage = this.dmg;

        if (target.isDefended()) {
            damage = Math.max(0, this.dmg - (this.dmg * target.getDefenseReduction() / 100));
            target.setDefended(false);
            System.out.println(target.getVards() + " bloķēja " + this.vards + " uzbrukumu un samazināja kaitējumu par " + target.getDefenseReduction() + "%!");
        }

        target.hp = Math.max(0, target.hp - damage);
        System.out.println(this.vards + " uzbrūk " + target.vards + " un nodara " + damage + " kaitējumu.");

        this.superCharge++;
    }
    
    @Override
    
    public String toString() {
        return "Vards: " + vards + "\nTrenera vārds: " + trainerVards + "\nPokemona HP: " + hp + "\nPokemona parastais uzbrukums: " + dmg + "\nPokemona ultimatīvais uzbrukums: " + ultDmg + "\nPokemona aizsardzība: " +def;
    }
    public String izvadit() {
        return this.toString();
    }

    public abstract void ult(Pokemons target);

    public abstract void uniqueMove();

    public abstract void healEnergy();
    
    public abstract void defense(Pokemons attacker);

    public abstract int getDefenseReduction();

    public abstract void superAttack(Pokemons target);

}