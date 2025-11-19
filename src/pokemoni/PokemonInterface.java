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
    }
}
        

    