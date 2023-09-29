package pokemon;

public enum Ability {
    ROUGH_SKIN("까칠한피부");

    private final String NAME;
    Ability(String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }
}
