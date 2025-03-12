package org.example;

public class Pokemon {
    private final String name;
    private final String type1;
    private final String type2;
    private final int total;
    private final int hp;
    private final int attack;
    private final int defense;
    private final int spAtk;
    private final int spDef;
    private final int speed;
    private final int generation;
    private final boolean legendary;
    private final String ability;

    public Pokemon(String name, String type1, String type2,
                   int total, int hp, int attack, int defense,
                   int spAtk, int spDef, int speed,
                   int generation, boolean legendary,
                   String ability) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.generation = generation;
        this.legendary = legendary;
        this.ability = ability;
    }

    // Solo los getters que usas realmente
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getAbility() { return ability; }

    // equals y hashCode basados en el nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon pokemon)) return false;
        return name.equals(pokemon.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    // toString para imprimir todos los datos
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", total=" + total +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", speed=" + speed +
                ", generation=" + generation +
                ", legendary=" + legendary +
                ", ability='" + ability + '\'' +
                '}';
    }
}
