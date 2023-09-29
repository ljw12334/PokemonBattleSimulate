package pokemon;

public enum Ability {
    ROUGH_SKIN("까칠한피부",
            "접촉한 상대에게 상처를 입힌다."), // 최대 체력의 1/8
    PRESSURE("프레셔",
            "상대가 사용하는 기술의 PP를 많이 줄인다."), // 기술 1회당 PP 2 감소
    POISON_POINT("독가시",
            "접촉한 상대를 중독 시킬 때가 있다."), // 30% 확률
    SERENE_GRACE("하늘의은총",
            "기술의 추가 효과가 나오기 쉽다."), // 확률 2배
    INNER_FOCUS("정신력",
            "풀죽지 않는다. 특성 위협으로 공격이 내려가지 않는다."),
    MARVEL_SCALE("이상한비늘",
            "상태 이상에 걸리면 방어가 올라간다."), // 방어 1.5배
    STORM_DRAIN("마중물",
            "물타입 기술을 모두 자신이 맞는다.\n물타입 기술을 맞으면 특수공격이 올라간다."), // 물타입 기술의 데미지 무시
    ADAPTABILITY("적응력",
            "타입이 같은 기술의 위력이 올라간다."); // 자속보정 1.5배 => 2.0배로 상승

    private final String NAME;
    private final String EXPLAIN;
    Ability(String name, String explain) {
        NAME = name;
        EXPLAIN = explain;
    }

    public String getName() {
        return NAME;
    }
    public String getEXPLAIN() {
        return EXPLAIN;
    }
}
