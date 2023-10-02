package pokemon;

public enum ItemList {
    // berryID : kind가 나무열매 일 시 부여, 나무열매가 아닌 경우 -1 부여
    SITRUS_BERRY("자뭉열매", "나무열매", 10, "회복",
            "포켓몬에게 지니게 하면 HP를 조금 회복한다."),
    OCCA_BERRY("오카열매", "나무열매", 36, "불꽃",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 불꽃 타입 기술의 위력을 약화시킨다."),
    PASSHO_BERRY("꼬시개열매", "나무열매", 37, "물",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 물 타입 기술의 위력을 약화시킨다."),
    WACAN_BERRY("초나열매", "나무열매", 38, "전기",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 전기 타입 기술의 위력을 약화시킨다."),
    RINDO_BERRY("린드열매", "나무열매", 39, "풀",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 풀 타입 기술의 위력을 약화시킨다."),
    YACHE_BERRY("플카열매", "나무열매", 40, "얼음",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 얼음 타입 기술의 위력을 약화시킨다."),
    CHOPLE_BERRY("로플열매", "나무열매", 41, "격투",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 격투 타입 기술의 위력을 약화시킨다."),
    KEBIA_BERRY("으름열매", "나무열매", 42, "독",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 독 타입 기술의 위력을 약화시킨다."),
    SHUCA_BERRY("슈캐열매", "나무열매", 43, "땅",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 땅 타입 기술의 위력을 약화시킨다."),
    COBA_BERRY("바코열매", "나무열매", 44, "비행",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 비행 타입 기술의 위력을 약화시킨다."),
    PAYAPA_BERRY("야파열매", "나무열매", 45, "에스퍼",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 에스퍼 타입 기술의 위력을 약화시킨다."),
    TANGA_BERRY("리체열매", "나무열매", 46, "벌레",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 벌레 타입 기술의 위력을 약화시킨다."),
    CHARTI_BERRY("루미열매", "나무열매", 47, "바위",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 바위 타입 기술의 위력을 약화시킨다."),
    KASIB_BERRY("수불열매", "나무열매", 48, "고스트",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 고스트 타입 기술의 위력을 약화시킨다."),
    HABAN_BERRY("하반열매", "나무열매", 49, "드래곤",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 드래곤 타입 기술의 위력을 약화시킨다."),
    COLBUR_BERRY("마코열매", "나무열매", 50, "악",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 악 타입 기술의 위력을 약화시킨다."),
    BABIRI_BERRY("바리비열매", "나무열매", 51, "강철",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 강철 타입 기술의 위력을 약화시킨다."),
    CHILAN_BERRY("카리열매", "나무열매", 52, "노말",
            "포켓몬에게 지니게 하면 단 한번 노말 타입 기술의 위력을 약화시킨다."),
    ROSEIL_BERRY("로셀열매", "나무열매", 65,"페어리",
            "포켓몬에게 지니게 하면 단 한번 효과가 뛰어난 페어리타입 기술의 위력을 약화시킨다."),
    LIFE_ORB("생명의구슬", "도구", -1, "데미지",
            "지니게 하면 공격할 때마다 HP가 조금씩 줄지만 기술의 위력이 올라간다."),
    EXPERT_BELT("달인의띠", "도구", -1, "데미지",
            "오래 사용한 까만 띠. 지니게 하면 효과가 뛰어날 때 기술의 위력이 조금 올라간다.");


    private final String NAME;
    private final String KIND;
    private final int BERRY_ID;
    private final String TYPE;
    private final String EXPLAIN;
    ItemList(String name, String kind, int berryID, String type, String explain) {
        NAME = name;
        KIND = kind;
        BERRY_ID = berryID;
        TYPE = type;
        EXPLAIN = explain;
    }
    public String getNAME() {
        return NAME;
    }
    public String getKIND() {
        return KIND;
    }
    public int getBERRY_ID() {
        return BERRY_ID;
    }
    public String getEXPLAIN() {
        return EXPLAIN;
    }
    public String getTYPE() {
        return TYPE;
    }
}
