package pokemon;

public enum PokemonList {
    MISSING_NO(0, "Missing No.", // 예외처리용
            Type.NONE, Type.NONE, 1.0f, 1.0f,
            new int[]{10, 10, 10, 10, 10, 10}),
    GARCHOMP(445, "한카리아스",
            Type.DRAGON, Type.GROUND, 1.9f, 95.0f,
            new int[]{108, 130, 95, 80, 85, 102}),
    SPIRITOMB(442, "화강돌",
            Type.GHOST, Type.DARK, 1.0f, 108.0f,
            new int[]{50, 92, 108, 92, 108, 35}),
    ROSERADE(407, "로즈레이드",
            Type.GRASS, Type.POISON, 0.9f, 14.5f,
            new int[]{60, 70, 65, 125, 105, 90}),
    TOGEKISS(468, "토게키스",
            Type.FAIRY, Type.FLYING, 1.5f, 38.0f,
            new int[]{85, 50, 95, 120, 115, 80}),
    LUCARIO(448, "루카리오",
            Type.FIGHTING, Type.STEEL, 1.2f, 54.0f,
            new int[]{70, 110, 70, 115, 70, 90}),
    MILOTIC(350, "밀로틱",
            Type.WATER, Type.NONE, 6.2f, 162.0f,
            new int[]{95, 60, 79, 100, 125, 81}),
    POLIGON_Z(474, "폴리곤Z",
            Type.NORMAL, Type.NONE, 0.9f, 34.0f,
            new int[]{85, 80, 70, 135, 75, 90}),
    GASTRODON(423, "트리토돈",
            Type.WATER, Type.GROUND, 0.9f, 29.9f,
            new int[]{111, 83, 68, 92, 82, 39}),
    TORTERRA(389, "토대부기",
            Type.GRASS, Type.GROUND, 2.2f, 310.0f,
            new int[]{95, 109, 105, 75, 85, 56}),
    INFERNAPE(392, "초염몽",
            Type.FIRE, Type.FIGHTING, 1.2f, 55.0f,
            new int[]{76, 104, 71, 104, 71, 108}),
    EMPOLEON(395, "엠페르트",
            Type.WATER, Type.STEEL, 1.7f, 84.5f,
            new int[]{84, 86, 88, 111, 101, 60}),
    STARAPTOR(398, "찌르호크",
            Type.NORMAL, Type.FLYING, 1.2f, 24.9f,
            new int[]{85, 120, 70, 50, 60, 100});



    private final int INDEX;
    private final String NAME;
    private final Type TYPE1, TYPE2;
    private final float HEIGHT, WEIGHT;
    private final int[] BASE_STATS;

    PokemonList(int index, String name, Type type1, Type type2, float height, float weight, int[] baseStats) {
        INDEX = index;
        NAME = name;
        TYPE1 = type1;
        TYPE2 = type2;
        HEIGHT = height;
        WEIGHT = weight;
        BASE_STATS = baseStats;

    }

    public int getIndex() {
        return INDEX;
    }
    public String getName() {
        return NAME;
    }
    public Type getType1() {
        return TYPE1;
    }
    public Type getType2() {
        return TYPE2;
    }
    public float getHeight() {
        return HEIGHT;
    }
    public float getWeight() {
        return WEIGHT;
    }
    public int[] getBaseStats() {
        return BASE_STATS;
    }
}
