<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png" width="400" />

# Pokémon Java — vienkārša spēle

Īss apraksts
- Vienkārša Java programma ar pokemoniem. Katrs pokemons ir objekts ar vārdu, trenera vārdu, HP, parasto uzbrukumu, ultī un aizsardzību.
- Interfeiss: vienkāršas Swing dialoglodziņas (JOptionPane) un grafiskais logs (PokemonInterface).

Kas programma dara
- Pievieno jaunu pokemona ar laukiem: vārds, treneris, HP, parastais uzbrukums, ultimatīvais uzbrukums, aizsardzība.
- Rāda visu pokemona sarakstu teksta logā.
- Dzēš izvēlētu pokemona no saraksta.
- Veic divu pokemona cīņu:
  - Katrs punkts gājienā izvēlas no darbībām: Uzbrukt, Aizsargāties, Supersitiens, Galīgais uzbrukums, Unikāls gājiens, Atjaunot enerģiju.
  - Katram tipam (Elektriskais, Ūdens, Akmens, Uguns) ir savs resurss (enerģija, ūdens, bruņas u.c.) un unikāla spēja, kas izmanto šo resursu.
  - Supersitiens (superAttack) ir spēcīga prasme, ko var izpildīt tikai, ja pokemons sakrāj superCharge (parasti ≥ 3).
  - Aizsardzība samazina nākamā uzbrukuma radīto kaitējumu (defense reduction).
