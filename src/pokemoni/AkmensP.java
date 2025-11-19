package pokemoni;

import javax.swing.JOptionPane;

public class AkmensP extends Pokemons {
    private int armor;

    public AkmensP(String Vards, String TrainerVards, int hp, int dmg, int ultdmg, int def) {
        super(Vards, TrainerVards, hp, dmg, ultdmg, def);
        this.armor = 30;
    }

    @Override
    public void ult(Pokemons target) {
        if (armor < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami daudz bruņu galīgajam uzbrukumam!");
            return;
        }

        JOptionPane.showMessageDialog(null, getVards() + " izmantoja galīgo uzbrukumu!");
        armor -= 20;
        int damage = Math.max(0, getUltDmg() - target.getDef());
        target.setHp(Math.max(0, target.getHp() - damage));
        JOptionPane.showMessageDialog(null,
                getVards() + " nodarīja " + damage + " kaitējumu " + target.getVards() + " ar savu galīgo uzbrukumu!\n" +
                target.getVards() + " ir palikuši " + target.getHp() + " HP.");
    }

    @Override
    public void uniqueMove() {
        if (armor < 10) {
            System.out.println(getVards() + " nav pietiekami daudz bruņu unikālajam solim! ❌");
            return;
        }
        armor -= 10;
        setDef(getDef() + 5);
        JOptionPane.showMessageDialog(null, getVards() + " palielina bruņas! 🛡 Aizsardzība palielināta par 5.");
    }

    @Override
    public void healEnergy() {
        if (armor < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno bruņas! 🛡 Bruņas +15.");
            armor += 15;
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno bruņas! 🛡 Bruņas +10.");
            armor += 10;
        }
    }

    @Override
    public void defense(Pokemons attacker) {
        JOptionPane.showMessageDialog(null, getVards() + " izmanto Akmens Apvalku!");
        setDefended(true);
        this.setSuperCharge(this.getSuperCharge() + 1);
    }

    @Override
    public int getDefenseReduction() {
        return 50;
    }

    @Override
    public void superAttack(Pokemons target) {
        if (this.getSuperCharge() >= 3) {
            JOptionPane.showMessageDialog(null, getVards() + " izmanto Kalnu Sabrukumu!");
            target.setHp(Math.max(0, target.getHp() - (getUltDmg() + 50)));
            JOptionPane.showMessageDialog(null, target.getVards() + " ātrums samazināts par 3 sekundēm un nevar uzbrukt!");
            this.setSuperCharge(0);
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami uzlādēta superspējas uzbrukumam!");
        }
    }
}