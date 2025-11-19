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
	
	public static void listPokemons(ArrayList<Pokemons> pokemon4iki) {
        StringBuilder builder = new StringBuilder();
        for (Pokemons pokemon : pokemon4iki) {
            builder.append(pokemon.getVards())
                   .append(", HP: ")
                   .append(pokemon.getHp())
                   .append("\n");
        }
        JTextArea textArea = new JTextArea(builder.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, scrollPane, "Pokemona saraksts", JOptionPane.INFORMATION_MESSAGE);
    }

	public static void battlePokemons(ArrayList<Pokemons> pokemon4iki) {
        Pokemons pokemon1 = choosePokemon(pokemon4iki, "Izvēlies pirmo pokemona cīņai");
        Pokemons pokemon2 = choosePokemon(pokemon4iki, "Izvēlies otro pokemona cīņai");

        if (pokemon1 == null || pokemon2 == null || pokemon1 == pokemon2) {
            JOptionPane.showMessageDialog(null, "Neskaidra pokemona izvēle cīņai!");
            return;
        }

        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            battleTurn(pokemon1, pokemon2);
            if (pokemon2.getHp() <= 0) break;

            battleTurn(pokemon2, pokemon1);
            if (pokemon1.getHp() <= 0) break;

            JOptionPane.showMessageDialog(null, pokemon1.getVards() + " HP: " + pokemon1.getHp() + "\n" + pokemon2.getVards() + " HP: " + pokemon2.getHp());
        }

        Pokemons winner = (pokemon1.getHp() > 0) ? pokemon1 : pokemon2;
        JOptionPane.showMessageDialog(null, winner.getVards() + " uzvar!");

        showWinnerImage(winner);
    }

    private static void showWinnerImage(Pokemons winner) {
        String imagePath = getPokemonImage(winner.getVards());
        ImageIcon icon = new ImageIcon(imagePath);

        JOptionPane.showMessageDialog(null, winner.getVards() + " uzvar!", "Uzvara!", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    private static String getPokemonImage(String pokemonName) {
        switch (pokemonName) {
            case "Pikachu":
                return "pikachu.jpg";
            case "Charizard":
                return "charizard.jpg";
            case "Squirtle":
                return "Squirtle.png";
            case "Onix":
                return "onix.jpg";
            default:
                return "unknown.jpg";
    }
   }

    public static Pokemons choosePokemon(ArrayList<Pokemons> pokemon4iki, String message) {
        String[] pokemonNames = pokemon4iki.stream().map(Pokemons::getVards).toArray(String[]::new);
        String chosenName = (String) JOptionPane.showInputDialog(null, message, "Izvēlies Pokemona", JOptionPane.QUESTION_MESSAGE, null, pokemonNames, pokemonNames[0]);
        return pokemon4iki.stream().filter(p -> p.getVards().equals(chosenName)).findFirst().orElse(null);
    }

    public static void battleTurn(Pokemons attacker, Pokemons defender) {
    	String[] actions = {"Uzbrukt", "Aizsargāties", "Supersitiens", "Galīgais uzbrukums", "Unikāls gājiens", "Atjaunot enerģiju"};
        String action = (String) JOptionPane.showInputDialog(null, attacker.getVards() + " izvēlieties darbību", "Darbība", JOptionPane.QUESTION_MESSAGE, null, actions, actions[0]);

        if ("Uzbrukt".equals(action)) {
            attacker.attack(defender);
        } else if ("Aizsargāties".equals(action)) {
            attacker.defense(attacker);
        } else if ("Supersitiens".equals(action)) {
            attacker.superAttack(defender);
        } else if ("Galīgais uzbrukums".equals(action)) {
            attacker.ult(defender);
        } else if ("Unikāls gājiens".equals(action)) {
            attacker.uniqueMove();
        } else if ("Atjaunot enerģiju".equals(action)) {
            attacker.healEnergy();
        }
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
                 
            case 2:
            	if (pokemon4iki.size() > 0) {
                    String str = "Pokemonu skaits ir: " + pokemon4iki.size() + "\n______________________________\n";
                    for (int i = 0; i < pokemon4iki.size(); i++) {
                        str += pokemon4iki.get(i).izvadit() + "\n______________________________\n";
                    }
                    JTextArea ta = new JTextArea(str, 10, 40);
                    JScrollPane sp = new JScrollPane(ta);
                    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    ta.setEditable(false);
                    JOptionPane.showMessageDialog(null, sp, "Pokemoni", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nav pievienoti Pokemoni!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 3: 
                battlePokemons(pokemon4iki);
                break;
            	
            case 4:
            	JOptionPane.showMessageDialog(null, "Programma tika apturēta", "Informācija", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Nepareiza izvēle", "Kļūda", JOptionPane.ERROR_MESSAGE);
            }
            
		}while(izvelesIndekss != 4);
	}
}
