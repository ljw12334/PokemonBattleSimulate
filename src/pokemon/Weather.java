package pokemon;

public enum Weather {
    NONE("", ""),
    HARSH_SUNLIGHT("쾌청", "햇살이 강하다"),
    EXTREAMELY_HARSH_SUNLIGHT("강한 쾌청", "햇살이 매우 강하다!"),
    RAIN("비", "비가 내리고 있다"),
    HEAVY_RAIN("강한 비", "비가 매우 거세게 내리고 있다!"),
    SANDSTORM("모래바람", "모래바람이 불고 있다"),
    HAIL("싸라기눈", "싸라기눈이 내리고 있다"),
    FOG("안개", "안개가 짙다");

    private final String NAME;
    private final String EXPLAIN;

    Weather(String name, String explain) {
        NAME = name;
        EXPLAIN = explain;
    }

    public String getNAME() {
        return NAME;
    }
    public String getEXPLAIN() {
        return EXPLAIN;
    }
}
