package pokemoni;
import javax.swing.JOptionPane;

public class UdensP extends Pokemons {
    private int udensLimen;

    public UdensP(String Vards, String TrainerVards, int hp, int dmg, int ultdmg, int def) {
        super(Vards, TrainerVards, hp, dmg, ultdmg, def);
        this.udensLimen = 30;
    }

    @Override
    public void ult(Pokemons target) {
        if (udensLimen < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami daudz enerģijas galīgajai uzbrukuma!");
            return;
        }

        JOptionPane.showMessageDialog(null, getVards() + " izmantoja galīgo uzbrukumu!");
        udensLimen -= 20;
        int damage = Math.max(0, getUltDmg() - target.getDef());
        target.setHp(Math.max(0, target.getHp() - damage));
        JOptionPane.showMessageDialog(null,
                getVards() + " nodarīja " + damage + " kaitējumu " + target.getVards() + " ar savu galīgo uzbrukumu!\n" +
                target.getVards() + " ir palikuši " + target.getHp() + " HP.");
    }

    @Override
    public void uniqueMove() {
        if (udensLimen < 10) {
            System.out.println(getVards() + " nav pietiekami daudz ūdens atjaunošanai! ❌");
            return;
        }
        udensLimen -= 10;
        setHp(getHp() + 10);
        System.out.println(getVards() + " uzsūc ūdeni un atjauno 10 HP! 💦 Pašreizējais HP: " + getHp());
    }

    @Override
    public void healEnergy() {
        if (udensLimen < 20) {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno ūdens līmeni! ⚡ Ūdens +15.");
            udensLimen += 15;
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " atjauno ūdens līmeni! ⚡ Ūdens +10.");
            udensLimen += 10;
        }
    }

    @Override
    public void defense(Pokemons attacker) {
        JOptionPane.showMessageDialog(null, getVards() + " izmanto Elastīgu Ūdeni!");
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
            JOptionPane.showMessageDialog(null, getVards() + " izmanto Cunami!");
            target.setHp(Math.max(0, target.getHp() - (getUltDmg() + 40)));
            JOptionPane.showMessageDialog(null, target.getVards() + " ir apgāzts uzbrukuma un nevar uzbrukt!");
            this.setSuperCharge(0);
        } else {
            JOptionPane.showMessageDialog(null, getVards() + " nav pietiekami uzlādēta superspējas uzbrukumam!");
        }
    }
}