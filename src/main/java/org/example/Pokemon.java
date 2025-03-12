package org.example;

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

    // Getters que sí se usan
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getAbility() { return ability; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon pokemon = (Pokemon) o;
        return name.equals(pokemon.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

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
