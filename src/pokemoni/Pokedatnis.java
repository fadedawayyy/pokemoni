package pokemoni;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Pokedatnis {
	
	static int PokemonaIzv(ArrayList<Pokemons> pokemon4iki) {
        String[] rSaraksts = new String[pokemon4iki.size()];
        for (int i = 0; i < rSaraksts.length; i++) {
            rSaraksts[i] = (((Pokemons) pokemon4iki.get(i)).getVards()) +
                    " " + (((Pokemons) pokemon4iki.get(i)).getTrainerVards());
        }
        String izveletais = (String)JOptionPane.showInputDialog(null,
                "Izvelies Pokemonu:", "Izvele", JOptionPane.QUESTION_MESSAGE,
                null, rSaraksts, rSaraksts[0]);
        return Arrays.asList(rSaraksts).indexOf(izveletais);

    }
	
	public static void main(String[] args) {
		
		String[] darbibas = {"Jauns Pokemons", "Dzest Pokemonu", "Pokemona saraksts","Pokemonu cīņa","Beigt darbu"};
		int izvelesIndekss;
		ArrayList<Pokemons> pokemon4iki = new ArrayList<>();
		
		  	pokemon4iki.add(new ElektriskaisP("Pikachu", "Ash", 100, 20, 40, 10)); 
	        pokemon4iki.add(new UgunsP("Charizard", "Ash", 100, 20, 40, 10)); 
	        pokemon4iki.add(new UdensP("Squirtle", "Misty", 100, 20, 40, 8)); 
	        pokemon4iki.add(new AkmensP("Onix", "Brock", 100, 20, 40, 13)); 
 
		
		do {
            String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);
            izvelesIndekss = Arrays.asList(darbibas).indexOf(izvele);
			
            switch(izvelesIndekss) {
            case 0: 	
            	 String[] tips = {"Elektriskais", "Uguns", "Ūdens", "Akmens"};
                 String tipsIzvele = (String) JOptionPane.showInputDialog(null, "Izvēlies Pokemona tipu",
                         "Izvēle", JOptionPane.QUESTION_MESSAGE, null, tips, tips[0]);
                 
                 String vards;
                 do {
                     vards = JOptionPane.showInputDialog("Ievadi Pokemona vārdu");
                 } while (!vards.matches("[a-zA-Z]+"));
                 
                 String treneris;
                 do {
                     treneris = JOptionPane.showInputDialog("Ievadi trenera vārdu");
                 } while (!treneris.matches("[a-zA-Z]+"));

                 int hp;
                 do {
                     hp = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona HP (maks 100)"));
                 } while (hp < 0 || hp > 100);

                 int dmg;
                 do {
                     dmg = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona parasto uzbrukumu (maks 20)"));
                 } while (dmg < 0 || dmg > 20);

                 int ultdmg;
                 do {
                     ultdmg = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona ultimatīvo uzbrukumu (maks 40)"));
                 } while (ultdmg < 0 || ultdmg > 40);

                 int def;
                 do {
                     def = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona aizsardzību (maks 15)"));
                 } while (def < 0 || def > 15);

                 Pokemons jaunsPokemon;
                 switch (tipsIzvele) {
                     case "Elektriskais":
                         jaunsPokemon = new ElektriskaisP(vards, treneris, hp, dmg, ultdmg, def);
                         pokemon4iki.add(jaunsPokemon);
                         break;
                     case "Uguns":
                         jaunsPokemon = new UgunsP(vards, treneris, hp, dmg, ultdmg, def);
                         pokemon4iki.add(jaunsPokemon);
                         break;
                     case "Ūdens":
                         jaunsPokemon = new UdensP(vards, treneris, hp, dmg, ultdmg, def);
                         pokemon4iki.add(jaunsPokemon);
                         break;
                     case "Akmens":
                         jaunsPokemon = new AkmensP(vards, treneris, hp, dmg, ultdmg, def);
                         pokemon4iki.add(jaunsPokemon);
                         break;
                     default:
                         JOptionPane.showMessageDialog(null, "Nepareizs Pokemona tips!");
                         
                 }
                 JOptionPane.showMessageDialog(null, "Jauns Pokemons izveidots: " + vards + " (" + tipsIzvele + ")");
                 break;
            case 1:           	
            	int izvIndekss;
                if(pokemon4iki.size()>0) {
                    izvIndekss = PokemonaIzv(pokemon4iki);
                    pokemon4iki.remove(izvIndekss);
                    JOptionPane.showMessageDialog(null,
                            "Pokemons ir dzest!", "Paziņojums",
                            JOptionPane.INFORMATION_MESSAGE);

                } else
                    JOptionPane.showMessageDialog(null,
                            "Saraksta nav neviens pkemons!", "Kļūda",
                            JOptionPane.ERROR_MESSAGE);
                break;
                 
            
            
		}while(izvelesIndekss != 4);
	}

