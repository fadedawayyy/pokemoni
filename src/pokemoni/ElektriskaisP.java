package pokemoni;

import javax.swing.JOptionPane;

public class ElektriskaisP extends Pokemons {
    private int energijaLimen;

    public ElektriskaisP(String Vards, String TrainerVards, int hp, int dmg, int ultdmg, int def) {
        super(Vards, TrainerVards, hp, dmg, ultdmg, def);
        this.energijaLimen = 30;
    }

    @Override
    public void ult(Pokemons target) {
        if (energijaLimen < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami daudz enerģijas galīgajam uzbrukumam!");
            return;
        }

        JOptionPane.showMessageDialog(null, getVards() + " izmantoja galīgo uzbrukumu!");
        energijaLimen -= 20;
        int damage = Math.max(0, getUltDmg() - target.getDef());
        target.setHp(Math.max(0, target.getHp() - damage));
        JOptionPane.showMessageDialog(null,
                getVards() + " nodarīja " + damage + " kaitējumu " + target.getVards() + " ar savu galīgo uzbrukumu!\n" +
                target.getVards() + " ir palikuši " + target.getHp() + " HP.");
    }

    @Override
    public void uniqueMove() {
        if (energijaLimen < 10) {
            System.out.println(getVards() + " nav pietiekami daudz enerģijas unikālajam solim! ❌");
            return;
        }
        energijaLimen -= 10;
        setDmg(getDmg() + 5);
        JOptionPane.showMessageDialog(null, getVards() + " pastiprina enerģiju! ⚡ Uzbrukuma spēks palielināts par 5.");
    }

    @Override
    public void healEnergy() {
        if (energijaLimen < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " uzlādējas! ⚡ Enerģija +15.");
            energijaLimen += 15;
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " uzlādējas! ⚡ Enerģija +10.");
            energijaLimen += 10;
        }
    }

    @Override
    public void defense(Pokemons attacker) {
        JOptionPane.showMessageDialog(null, getVards() + " izmanto Strāvas Barjeru!");
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
            JOptionPane.showMessageDialog(null, getVards() + " izmanto Pērkona Izlādi!");
            target.setHp(Math.max(0, target.getHp() - (getUltDmg() + 50)));
            JOptionPane.showMessageDialog(null, target.getVards() + " ir paralizēts uz 2 sekundēm un nevar uzbrukt!");
            this.setSuperCharge(0);
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami uzlādēta superspējas uzbrukumam!");
        }
    }
}