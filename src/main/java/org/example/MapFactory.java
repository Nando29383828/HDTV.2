package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {

    /**
     * Devuelve una implementación de Map según la opción ingresada:
     * "1" -> HashMap
     * "2" -> TreeMap
     * "3" -> LinkedHashMap
     */
    public static Map<String, Pokemon> getMap(String option) {
        return switch (option) {
            case "1" -> new HashMap<>();
            case "2" -> new TreeMap<>();
            case "3" -> new LinkedHashMap<>();
            default -> {
                System.out.println("Opción inválida, se usará HashMap por defecto.");
                yield new HashMap<>();
            }
        };
    }
}
