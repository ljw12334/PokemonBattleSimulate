package pokemon;

public enum Nature {
    // 1, 2, 3, 4, 5 = 공, 방, 특공, 특방, 스피드
    HARDY("노력", 1, 1),
    LONELY("외로움", 1, 2),
    ADAMANT("고집", 1, 3),
    NAUGHTLY("개구쟁이", 1, 4),
    BRAVE("용감", 1, 5),

    BOLD("대담", 2, 1),
    DOCILE("온순", 2, 2),
    IMPISH("장난꾸러기", 2, 3),
    LAX("촐랑", 2, 4),
    RELAXED("무사태평", 2, 5),

    MODEST("조심", 3, 1),
    MILD("의젓", 3, 2),
    BASHFUL("수줍음", 3, 3),
    RASH("덜렁", 3, 4),
    QUIET("냉정", 3, 5),

    CALM("차분", 4, 1),
    GENTLE("얌전", 4, 2),
    CAREFUL("신중", 4, 3),
    QUIRKY("변덕", 4, 4),
    SASSY("건방", 4, 5),

    TIMID("겁쟁이", 5, 1),
    HASTY("성급", 5, 2),
    JOLLY("명랑", 5, 3),
    NAIVE("천진난만", 5, 4),
    SERIOUS("성실", 5, 5);



    private final String NAME;
    private final int UP_STAT, DOWN_STAT;

    Nature(String name, int upStat, int downStat) {
        NAME = name;
        UP_STAT = upStat;
        DOWN_STAT = downStat;
    }

    public String getName() {
        return NAME;
    }
    public int getUpStat() {
        return UP_STAT;
    }
    public int getDownStat() {
        return DOWN_STAT;
    }
}
