package pokemoni;

import javax.swing.JOptionPane;

public class UgunsP extends Pokemons {
    private int ugunsSpeks;

    public UgunsP(String Vards, String TrainerVards, int hp, int dmg, int ultdmg, int def) {
        super(Vards, TrainerVards, hp, dmg, ultdmg, def);
        this.ugunsSpeks = 30;
    }

    @Override
    public void ult(Pokemons target) {
        if (ugunsSpeks < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami daudz uguns spēka galīgajam uzbrukumam!");
            return;
        }

        JOptionPane.showMessageDialog(null, getVards() + " izmantoja galīgo uzbrukumu!");
        ugunsSpeks -= 20;
        int damage = Math.max(0, getUltDmg() - target.getDef());
        target.setHp(Math.max(0, target.getHp() - damage));
        JOptionPane.showMessageDialog(null,
                getVards() + " nodarīja " + damage + " kaitējumu " + target.getVards() + " ar savu galīgo uzbrukumu!\n" +
                target.getVards() + " ir palikuši " + target.getHp() + " HP.");
    }

    @Override
    public void uniqueMove() {
        if (ugunsSpeks < 10) {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami daudz uguns unikālajam solim!");
            return;
        }
        ugunsSpeks -= 10;
        setDmg(getDmg() + 5);
        JOptionPane.showMessageDialog(null, getVards() + " pastiprina liesmu! 🔥 Uzbrukuma spēks palielināts par 5.");
    }

    @Override
    public void healEnergy() {
        if (ugunsSpeks < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno uguni! 🔥 Uguns +15.");
            ugunsSpeks += 15;
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno uguni! 🔥 Uguns +10.");
            ugunsSpeks += 10;
        }
    }

    @Override
    public void defense(Pokemons attacker) {
        JOptionPane.showMessageDialog(null, getVards() + " izmanto Ugunīgo Vairogu!");
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
            JOptionPane.showMessageDialog(null, getVards() + " izmanto Ellejošo Liesmu!");
            target.setHp(Math.max(0, target.getHp() - (getUltDmg() + 50)));
            JOptionPane.showMessageDialog(null, target.getVards() + " deg un zaudē dzīvotspēju 5 sekundēs!");
            this.setSuperCharge(0);
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami uzlādēta superspējas uzbrukumam!");
        }
    }
}