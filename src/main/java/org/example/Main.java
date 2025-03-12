package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    // Map con todos los pokemons leídos
    private static Map<String, Pokemon> allPokemons;
    // Colección del usuario (HashSet evita duplicados en O(1))
    private static Set<Pokemon> userCollection;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Elegir implementación de Map
        System.out.println("Seleccione la implementación de Map a usar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        System.out.print("Opción: ");
        String choice = sc.nextLine();

        // Usar el Factory
        allPokemons = MapFactory.getMap(choice);

        // Leer el CSV
        String csvFile = "pokemon_data_pokeapi.csv"; // Ajusta si está en otra carpeta
        readCSV(csvFile);

        // Inicializar colección del usuario
        userCollection = new HashSet<>();

        // Menú de operaciones
        int option;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar un Pokémon a la colección del usuario.");
            System.out.println("2. Mostrar los datos de un Pokémon.");
            System.out.println("3. Mostrar (nombre, type1) de Pokémon en la colección, ordenados por type1.");
            System.out.println("4. Mostrar (nombre, type1) de TODOS los Pokémon, ordenados por type1.");
            System.out.println("5. Mostrar Pokémon que tengan la habilidad indicada.");
            System.out.println("0. Salir.");
            System.out.print("Ingrese su opción: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                option = -1;
            }

            switch (option) {
                case 1:
                    agregarPokemon(sc);
                    break;
                case 2:
                    mostrarDatosPokemon(sc);
                    break;
                case 3:
                    mostrarColeccionUsuario();
                    break;
                case 4:
                    mostrarTodosPokemons();
                    break;
                case 5:
                    mostrarPokemonsPorHabilidad(sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no reconocida, intente de nuevo.");
            }
        } while (option != 0);

        sc.close();
    }

    /**
     * Lee el CSV y carga los Pokémon en el Map 'allPokemons'.
     * Se asume un CSV con 13 columnas:
     *  Name, Type1, Type2, Total, HP, Attack, Defense,
     *  Sp. Atk, Sp. Def, Speed, Generation, Legendary, Ability.
     */
    private static void readCSV(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Descartar la cabecera
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 13) {
                    continue;
                }
                String name = fields[0].trim();
                String type1 = fields[1].trim();
                String type2 = fields[2].trim();
                int total = Integer.parseInt(fields[3].trim());
                int hp = Integer.parseInt(fields[4].trim());
                int attack = Integer.parseInt(fields[5].trim());
                int defense = Integer.parseInt(fields[6].trim());
                int spAtk = Integer.parseInt(fields[7].trim());
                int spDef = Integer.parseInt(fields[8].trim());
                int speed = Integer.parseInt(fields[9].trim());
                int generation = Integer.parseInt(fields[10].trim());
                boolean legendary = Boolean.parseBoolean(fields[11].trim());
                String ability = fields[12].trim();

                Pokemon p = new Pokemon(
                        name, type1, type2,
                        total, hp, attack, defense,
                        spAtk, spDef, speed,
                        generation, legendary,
                        ability
                );

                allPokemons.put(name, p);
            }
            System.out.println("Se han cargado " + allPokemons.size() + " Pokémon del CSV.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el CSV: " + e.getMessage());
        }
    }

    // 1. Agregar Pokémon
    private static void agregarPokemon(Scanner sc) {
        System.out.print("Ingrese el nombre del Pokémon: ");
        String nombre = sc.nextLine().trim();

        if (!allPokemons.containsKey(nombre)) {
            System.out.println("ERROR: Ese Pokémon no está en la base de datos.");
            return;
        }

        Pokemon p = allPokemons.get(nombre);
        if (userCollection.contains(p)) {
            System.out.println("Ya tienes ese Pokémon en tu colección.");
        } else {
            userCollection.add(p);
            System.out.println("Pokémon agregado a tu colección.");
        }
    }

    // 2. Mostrar datos completos
    private static void mostrarDatosPokemon(Scanner sc) {
        System.out.print("Ingrese el nombre del Pokémon: ");
        String nombre = sc.nextLine().trim();

        if (!allPokemons.containsKey(nombre)) {
            System.out.println("ERROR: Ese Pokémon no está en la base de datos.");
            return;
        }
        Pokemon p = allPokemons.get(nombre);
        System.out.println("Datos completos del Pokémon:");
        System.out.println(p);
    }

    // 3. Mostrar la colección del usuario ordenada por type1
    private static void mostrarColeccionUsuario() {
        if (userCollection.isEmpty()) {
            System.out.println("Tu colección está vacía.");
            return;
        }
        List<Pokemon> lista = new ArrayList<>(userCollection);
        lista.sort(Comparator.comparing(Pokemon::getType1));

        System.out.println("Pokémon en tu colección (ordenados por type1):");
        for (Pokemon p : lista) {
            System.out.println("Nombre: " + p.getName() + ", Type1: " + p.getType1());
        }
    }

    // 4. Mostrar todos los Pokémon ordenados por type1
    private static void mostrarTodosPokemons() {
        if (allPokemons.isEmpty()) {
            System.out.println("No hay Pokémon en la base de datos.");
            return;
        }
        List<Pokemon> lista = new ArrayList<>(allPokemons.values());
        lista.sort(Comparator.comparing(Pokemon::getType1));

        System.out.println("Todos los Pokémon (ordenados por type1):");
        for (Pokemon p : lista) {
            System.out.println("Nombre: " + p.getName() + ", Type1: " + p.getType1());
        }
    }

    // 5. Mostrar Pokémon que tengan la habilidad indicada
    private static void mostrarPokemonsPorHabilidad(Scanner sc) {
        System.out.print("Ingrese la habilidad que busca: ");
        String habilidad = sc.nextLine().trim();

        boolean encontrado = false;
        for (Pokemon p : allPokemons.values()) {
            if (p.getAbility().equalsIgnoreCase(habilidad)) {
                System.out.println("Nombre: " + p.getName() + ", Ability: " + p.getAbility());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron Pokémon con la habilidad: " + habilidad);
        }
    }


}
