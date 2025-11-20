package pokemoni;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PokemonInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private ArrayList<Pokemons> pokemon4iki;
    private JTextArea ta;

    static int PokemonaIzv(ArrayList<Pokemons> pokemon4iki) {
        String[] rSaraksts = new String[pokemon4iki.size()];
        for (int i = 0; i < rSaraksts.length; i++) {
            rSaraksts[i] = (((Pokemons) pokemon4iki.get(i)).getVards()) +
                    " " + (((Pokemons) pokemon4iki.get(i)).getTrainerVards());
        }
        String izveletais = (String)JOptionPane.showInputDialog(null,
                "Izvēlies Pokemona:", "Izvēle", JOptionPane.QUESTION_MESSAGE,
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
        EventQueue.invokeLater(() -> {
            try {
                PokemonInterface frame = new PokemonInterface();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PokemonInterface() {
        setTitle("Pokémon Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 429);

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 223, 186));
        panel.setLayout(null);
        contentPane.add(panel, "MainScreen");

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\javaeclipse\\Pokemon44iki\\pngwing.com (1).png"));
        lblNewLabel.setBounds(239, 113, 150, 150);
        panel.add(lblNewLabel);

        JButton btnNewButton = new JButton("PLAY");
        btnNewButton.setFont(new Font("Pokemon Solid", Font.BOLD, 25));
        btnNewButton.setBounds(223, 274, 175, 59);
        btnNewButton.setBackground(new Color(255, 85, 85));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.add(btnNewButton);

        JLabel titleLabel = new JLabel("POKEMONI");
        titleLabel.setFont(new Font("Pokemon Solid", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(84, 11, 473, 98);
        titleLabel.setForeground(new Color(255, 85, 85));
        panel.add(titleLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 223, 186));
        panel_1.setLayout(null);
        contentPane.add(panel_1, "GameScreen");

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Pokemon Solid", Font.PLAIN, 13));
        btnBack.setBounds(10, 11, 90, 34);
        btnBack.setBackground(new Color(85, 85, 255));
        btnBack.setForeground(Color.WHITE);
        panel_1.add(btnBack);

        JButton btnOption1 = new JButton("Jauns pokemons");
        btnOption1.setFont(new Font("Pokemon Solid", Font.PLAIN, 13));
        btnOption1.addActionListener(e -> addNewPokemon());
        btnOption1.setBounds(0, 112, 157, 79);
        btnOption1.setBackground(new Color(255, 255, 85));
        panel_1.add(btnOption1);

        JButton btnOption2 = new JButton("Pokemonu saraksts");
        btnOption2.setFont(new Font("Pokemon Solid", Font.PLAIN, 12));
        btnOption2.setBounds(330, 112, 157, 79);
        btnOption2.addActionListener(e -> displayPokemonList());
        btnOption2.setBackground(new Color(85, 85, 255));
        panel_1.add(btnOption2);

        JButton btnNewButton_1 = new JButton("Dzest pokemonu");
        btnNewButton_1.setFont(new Font("Pokemon Solid", Font.PLAIN, 12));
        btnNewButton_1.setBounds(167, 112, 157, 79);
        btnNewButton_1.addActionListener(e -> deletePokemon());
        btnNewButton_1.setBackground(new Color(255, 85, 85));
        panel_1.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Pokemonu ciņa");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pokemonuCina();
        	}
        });
        btnNewButton_2.setFont(new Font("Pokemon Solid", Font.PLAIN, 12));
        btnNewButton_2.setBounds(497, 112, 157, 79);
        btnNewButton_2.setBackground(new Color(85, 255, 85));
        panel_1.add(btnNewButton_2);
        
        JLabel lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\pngwing.com (1).png"));
        lblNewLabel_1.setBounds(231, 197, 150, 150);
        panel_1.add(lblNewLabel_1);

        btnNewButton.addActionListener(e -> cardLayout.show(contentPane, "GameScreen"));
        btnBack.addActionListener(e -> cardLayout.show(contentPane, "MainScreen"));

        pokemon4iki = new ArrayList<>();
        pokemon4iki.add(new ElektriskaisP("Pikachu", "Ash", 100, 20, 50, 10)); 
        pokemon4iki.add(new UgunsP("Charizard", "Ash", 100, 25, 60, 15)); 
        pokemon4iki.add(new UdensP("Squirtle", "Misty", 100, 18, 45, 8)); 
        pokemon4iki.add(new AkmensP("Onix", "Brock", 100, 30, 50, 20)); 

        ta = new JTextArea(10, 40);
    }

    private void addNewPokemon() {
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
        } while (hp < 1 || hp > 100);

        int dmg;
        do {
            dmg = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona parasto uzbrukumu (maks 20)"));
        } while (dmg < 1 || dmg > 20);

        int ultdmg;
        do {
            ultdmg = Integer.parseInt(JOptionPane.showInputDialog("Ievadi Pokemona ultimatīvo uzbrukumu (maks 40)"));
        } while (ultdmg < 1 || ultdmg > 40);

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
    }

    private void deletePokemon() {
        int izvIndekss;
        if (pokemon4iki.size() > 0) {
            izvIndekss = PokemonaIzv(pokemon4iki);
            pokemon4iki.remove(izvIndekss);
            JOptionPane.showMessageDialog(null,
                    "Pokemons ir dzest!", "Paziņojums",
                    JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null,
                    "Saraksta nav neviens pkemons!", "Kļūda",
                    JOptionPane.ERROR_MESSAGE);
    }

    private void displayPokemonList() {
        if (pokemon4iki.size() > 0) {
            String str = "Pokemonu skaits ir: " + pokemon4iki.size() + "\n______________________________\n";
            for (int i = 0; i < pokemon4iki.size(); i++) {
                str += pokemon4iki.get(i).izvadit() + "\n______________________________\n";
            }
            ta.setText(str);
            ta.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Pokemoni", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nav pievienoti Pokemoni!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void pokemonuCina() {
    	battlePokemons(pokemon4iki);
    }
}
//
