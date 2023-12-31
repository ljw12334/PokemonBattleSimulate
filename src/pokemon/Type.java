package pokemon;

public enum Type {
    NONE("", // 빈 타입
            new String[]{},
            new String[]{},
            new String[]{}),
    NORMAL("노말",
            new String[]{},
            new String[]{"바위", "강철"},
            new String[]{"고스트"}),
    FIRE("불꽃",
            new String[]{"풀", "얼음", "벌레", "강철"},
            new String[]{"불꽃", "물", "바위", "드래곤"},
            new String[]{}),
    WATER("물",
            new String[]{"불꽃", "땅", "바위"},
            new String[]{"물", "풀", "드래곤"},
            new String[]{}),
    GRASS("풀",
            new String[]{"물", "땅", "바위"},
            new String[]{"불꽃", "풀", "독", "비행", "벌레", "드래곤", "강철"},
            new String[]{}),
    ELECTRIC("전기",
            new String[]{"물", "비행"},
            new String[]{"풀", "전기", "드래곤"},
            new String[]{"땅"}),
    ICE("얼음",
            new String[]{"풀", "땅", "비행", "드래곤"},
            new String[]{"불꽃", "물", "얼음", "강철"},
            new String[]{}),
    FIGHTING("격투",
            new String[]{"노말", "얼음", "바위", "악", "강철"},
            new String[]{"독", "비행", "에스퍼", "벌레", "페어리"},
            new String[]{"고스트"}),
    POISON("독",
            new String[]{"풀", "페어리"},
            new String[]{"독", "땅", "바위", "고스트"},
            new String[]{"강철"}),
    GROUND("땅",
            new String[]{"불꽃", "전기", "독", "바위", "강철"},
            new String[]{"풀", "벌레"},
            new String[]{"비행"}),
    FLYING("비행",
            new String[]{"풀", "격투", "벌레"},
            new String[]{"전기", "바위", "강철"},
            new String[]{}),
    PSYCHIC("에스퍼",
            new String[]{"격투", "독"},
            new String[]{"에스퍼", "강철"},
            new String[]{"악"}),
    BUG("벌레",
            new String[]{"풀", "에스퍼", "악"},
            new String[]{"불꽃", "격투", "독", "비행", "고스트", "강철", "페어리"},
            new String[]{}),
    ROCK("바위",
            new String[]{"불꽃", "얼음", "비행", "벌레"},
            new String[]{"격투", "땅", "강철"},
            new String[]{}),
    GHOST("고스트",
            new String[]{"에스퍼", "고스트"},
            new String[]{"악"},
            new String[]{"노말"}),
    DRAGON("드래곤",
            new String[]{"드래곤"},
            new String[]{"강철"},
            new String[]{"페어리"}),
    DARK("악",
            new String[]{"에스퍼", "고스트"},
            new String[]{"격투", "악", "페어리"},
            new String[]{}),
    STEEL("강철",
            new String[]{"얼음", "바위", "페어리"},
            new String[]{"불꽃", "물", "전기", "강철"},
            new String[]{}),
    FAIRY("페어리",
            new String[]{"격투", "드래곤", "악"},
            new String[]{"불꽃", "독", "강철"},
            new String[]{});


    private final String NAME;
    // 공격 상성 | 효과가 굉장한 타입 (데미지 x2), 효과가 별로인 타입 (데미지 x1/2), 효과가 없는 타입 (데미지 x0)
    private final String[] SUPER_EFFECTIVE, NOT_VERY_EFFECTIVE, NO_EFFECT;
    Type(String name, String[] superEffective, String[] notVeryEffective, String[] noEffect) {
        NAME = name;
        SUPER_EFFECTIVE = superEffective;
        NOT_VERY_EFFECTIVE = notVeryEffective;
        NO_EFFECT = noEffect;
    }

    public String getNAME() {
        return NAME;
    }

    public String[] getSUPER_EFFECTIVE() {
        return SUPER_EFFECTIVE;
    }

    public String[] getNOT_VERY_EFFECTIVE() {
        return NOT_VERY_EFFECTIVE;
    }

    public String[] getNO_EFFECT() {
        return NO_EFFECT;
    }
}
