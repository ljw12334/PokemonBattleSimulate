package pokemon;

public enum MoveList {

    SHADOW_BALL("섀도볼", Type.GHOST, Kind.SPECIAL, 80, 100, 15, 0, false,
            new EffectList[]{EffectList.DOWN_D},
            new Target[]{Target.FOE},
            new int[]{1},
            20, "까만 그림자의 덩어리를 내던져서 공격한다.\n상대의 특수방어를 떨어뜨릴 때가 있다."),
    DARK_PULSE("악의파동", Type.DARK, Kind.SPECIAL, 80, 100, 15, 0, false,
            new EffectList[]{EffectList.FLINCH},
            new Target[]{Target.FOE},
            new int[]{-1},
            20, "몸에서 악의로 가득한 무서운 오라를 발산한다.\n상대를 풀죽게 만들 때가 있다."),
    WILL_O_WISP("도깨비불", Type.FIRE, Kind.STATUS, -1, 85, 15, 0, false,
            new EffectList[]{EffectList.FLINCH},
            new Target[]{Target.FOE},
            new int[]{-1},
            100, "으스스하고 괴상한 불꽃을 쏘아 상대를 화상 상태로 만든다."),
    SUCKER_PUNCH("기습", Type.DARK, Kind.PHYSICAL, 70, 100, 5, 1, true,
            new EffectList[]{EffectList.SPECIAL_EFFECT}, // 상대가 공격기를 쓰지 않거나 상대보다 늦게 행동하면 실패함
            new Target[]{},
            new int[]{},
            -1, "상대보다 먼저 공격할 수 있다.\n상대가 쓴 기술이 공격기술이 아니면 실패한다."),
    DAZZLING_GLEAM("매지컬샤인", Type.FAIRY, Kind.SPECIAL, 80, 100, 10, 0, false,
            new EffectList[]{},
            new Target[]{},
            new int[]{},
            -1, "강력한 빛을 내어 상대에게 데미지를 준다."),
    SLUDGE_BOMB("오물폭탄", Type.POISON, Kind.SPECIAL, 90, 100, 10, 0, false,
            new EffectList[]{EffectList.POISON},
            new Target[]{Target.FOE},
            new int[]{-1},
            30, "더러운 오물을 상대에게 내던져서 공격한다.\n독 상태로 만들 때가 있다."),
    ENERGY_BALL("에너지볼", Type.GRASS, Kind.SPECIAL, 90, 100, 10, 0, false,
            new EffectList[]{EffectList.DOWN_D},
            new Target[]{Target.FOE},
            new int[]{1},
            10, "자연으로부터 생명의 힘을 모아서 발사한다.\n상대의 특수방어를 떨어뜨릴 때가 있다."),
    AIR_SLASH("에어슬래시", Type.FLYING, Kind.SPECIAL, 75, 95, 15, 0, false,
            new EffectList[]{EffectList.FLINCH},
            new Target[]{Target.FOE},
            new int[]{-1},
            30, "하늘까지 베어 가르는 공기의 칼날로 공격한다.\n상대를 풀죽게 만들 때가 있다."),
    AURA_SPHERE("파동탄", Type.FIGHTING, Kind.SPECIAL, 80, -1, 20, 0, false,
            new EffectList[]{},
            new Target[]{},
            new int[]{},
            -1, "몸속에서 파동의 힘을 끌어내 쏜다.\n공격은 반드시 명중한다."),
    THUNDER_WAVE("전기자석파", Type.ELECTRIC, Kind.STATUS, -1, 90, 20, 0, false,
            new EffectList[]{EffectList.PARALYSIS},
            new Target[]{Target.FOE},
            new int[]{-1},
            100, "약한 전격을 날려서 상대를 마비 상태로 만든다."),
    DRAGON_PULSE("용의파동", Type.DRAGON, Kind.SPECIAL, 85, 100, 10, 0, false,
            new EffectList[]{},
            new Target[]{},
            new int[]{},
            -1, "큰 입으로 충격파를 일으켜서 상대를 공격한다."),
    FLASH_CANNON("러스터캐논", Type.STEEL, Kind.SPECIAL, 80, 100, 10, 0, false,
            new EffectList[]{EffectList.DOWN_D},
            new Target[]{Target.FOE},
            new int[]{1},
            10, "몸의 빛을 한곳에 모아서 힘을 쏜다.\n상대의 특수방어를 떨어뜨릴 때가 있다."),
    PSYCHIC("사이코키네시스", Type.PSYCHIC, Kind.SPECIAL, 90, 100, 10, 0, false,
            new EffectList[]{EffectList.DOWN_D},
            new Target[]{Target.FOE},
            new int[]{1},
            10, "강한 염동력을 상대에게 보내어 공격한다.\n상대의 특수방어를 떨어뜨릴 때가 있다."),
    RECOVER("HP회복", Type.NORMAL, Kind.STATUS, -1, -1, 5, 0, false,
            new EffectList[]{EffectList.RECOVER_MAXHP},
            new Target[]{Target.USER},
            new int[]{50},
            100, "세포를 재생시켜 자신의 최대 HP의\n절반만큼 HP를 회복한다."),
    MIRROR_COAT("미러코트", Type.PSYCHIC, Kind.SPECIAL, 999, 100, 20, -5, false,
            new EffectList[]{EffectList.SPECIAL_EFFECT}, // 사용한 턴에, 상대가 사용한 특수기에 명중해야 성공함
            new Target[]{},
            new int[]{},
            -1, "상대에게 받은 특수공격의 데미지를 2배로 만들어\n그 상대에게 돌려준다."),
    ICE_BEAM("냉동빔", Type.ICE, Kind.SPECIAL, 90, 100, 10, 0, false,
            new EffectList[]{EffectList.FREEZE},
            new Target[]{Target.FOE},
            new int[]{-1},
            10, "냉동빔을 상대에게 발사하여 공격한다.\n얼음 상태로 만들 때가 있다."),
    SCALD("열탕", Type.WATER, Kind.SPECIAL, 80, 100, 15, 0, false,
            new EffectList[]{EffectList.BURN, EffectList.SPECIAL_EFFECT}, // 자신과 상대의 얼음 상태이상 치유
            new Target[]{Target.FOE},
            new int[]{-1},
            30, "뜨겁게 끓어오르는 물을 상대에게 발사해서 공격한다.\n화상 상태로 만들 때가 있다."),
    DRAGON_CLAW("드래곤클로", Type.DRAGON, Kind.PHYSICAL, 80, 100, 15, 0, true,
            new EffectList[]{},
            new Target[]{},
            new int[]{},
            -1, "날카롭고 뾰족한 거대한 발톱으로 상대를 베어 갈라서 공격한다."),
    EARTHQUAKE("지진", Type.GROUND, Kind.PHYSICAL, 100, 100, 10, 0, false,
            new EffectList[]{EffectList.SPECIAL_EFFECT}, // 구멍파기 중인 상대에게 데미지 2배
            new Target[]{},
            new int[]{},
            -1, "지진의 충격으로 주위에 있는 모든 것을 공격한다."),
    SWORDS_DANCE("칼춤", Type.NORMAL, Kind.STATUS, -1, -1, 20, 0, false,
            new EffectList[]{EffectList.UP_A},
            new Target[]{Target.USER},
            new int[]{2},
            100, "큰 입으로 충격파를 일으켜서 상대를 공격한다."),
    POISON_JAB("독찌르기", Type.POISON, Kind.PHYSICAL, 80, 100, 20, 0, true,
            new EffectList[]{EffectList.POISON},
            new Target[]{Target.FOE},
            new int[]{-1},
            30, "독에 물든 촉수나 팔로 상대를 꿰찌른다.\n독 상태로 만들 때가 있다."),
    HYPER_BEAM("파괴광선", Type.NORMAL, Kind.SPECIAL, 150, 90, 5, 0, false,
            new EffectList[]{EffectList.STUN},
            new Target[]{Target.USER},
            new int[]{-1},
            100, "강한 광선을 상대에게 발사하여 공격한다.\n다음 턴은 움직일 수 없다."),
    THUNDERBOLT("10만볼트", Type.ELECTRIC, Kind.SPECIAL, 90, 100, 15, 0, false,
            new EffectList[]{EffectList.PARALYSIS},
            new Target[]{Target.FOE},
            new int[]{-1},
            10, "강한 전격을 상대에게 날려서 공격한다.\n마비 상태로 만들 때가 있다."),
    ROCK_TOMB("암석봉인", Type.ROCK, Kind.PHYSICAL, 60, 95, 15, 0, false,
            new EffectList[]{EffectList.DOWN_S},
            new Target[]{Target.FOE},
            new int[]{1},
            100, "암석을 내던져서 공격한다.\n상대의 움직임을 봉인함으로써 스피드를 떨어뜨린다..");



    private final String NAME; // 기술 이름
    private final Type TYPE; // 기술 타입
    private final Kind KIND; // 물리, 특수, 변화 구분
    private final int POWER; // 위력 | 위력이 존재하지 않는 경우 -1, 별도의 계산식을 쓰는 경우 999
    private final int ACCURARY; // 명중률 | 필중기거나 자신에게 쓰는 변화기일 경우 -1
    private final int PP; // 기술 pp
    private final int PRIORITY; // 우선도 | default : 0, 숫자가 클수록 빠르게 사용함
    private final boolean IS_CONTACT; // 접촉기 여부
    private final EffectList[] EFFECT; // 부가 효과들
    private final Target[] TARGET; // 부가 효과 타겟
    private final int[] RANK; // 부가 효과 수준 | 수준이 따로 없는 효과의 경우 : -1 적용
    private final int CHANCE; // 발동 확률 0 ~ 100 | 효과가 따로 없는 경우 -1
    private final String EXPLAIN; // 기술 설명문


    MoveList(String name, Type type, Kind kind, int power, int accurary, int pp, int priority, boolean isContact,
             EffectList[] effect, Target[] target, int[] rank, int chance, String explain) {
        NAME = name;
        TYPE = type;
        KIND = kind;
        POWER = power;
        ACCURARY = accurary;
        PP = pp;
        PRIORITY = priority;
        IS_CONTACT = isContact;
        EFFECT = effect;
        TARGET = target;
        RANK = rank;
        CHANCE = chance;
        EXPLAIN = explain;
    }

    public enum Kind {PHYSICAL, SPECIAL, STATUS};
    public enum Target{USER, FOE};
    public enum EffectList {
        POISON, TOXIC, FREEZE, PARALYSIS, SLEEP, BURN, // 일반 지속 상태이상
        UP_A, UP_B, UP_C, UP_D, UP_S, // 랭크 업
        DOWN_A, DOWN_B, DOWN_C, DOWN_D, DOWN_S, // 랭크 다운
        CONFUSION, FLINCH, BINDING, // 혼란, 풀죽음, 도망불가
        STUN, REBOUND, RECOVER_MAXHP, RECOVER_DAMAGE, // 명중시 한턴쉬기, 반동데미지, 최대체력 기준 회복, 준 데미지 기준 회복 | 퍼센티지 0 ~ 100
        SPECIAL_EFFECT // 특정 기술에만 있는 특수한 효과나 패널티
    }

    public String getName() {
        return NAME;
    }

    public Type getType() {
        return TYPE;
    }

    public Kind getKind() {
        return KIND;
    }

    public int getPower() {
        return POWER;
    }

    public int getAccurary() {
        return ACCURARY;
    }

    public int getPp() {
        return PP;
    }

    public int getPriority() {
        return PRIORITY;
    }

    public boolean isContact() {
        return IS_CONTACT;
    }

    public EffectList[] getEffect() {
        return EFFECT;
    }

    public Target[] getTarget() {
        return TARGET;
    }

    public int[] getRank() {
        return RANK;
    }

    public int getChance() {
        return CHANCE;
    }

    public String getExplain() {
        return EXPLAIN;
    }

}
