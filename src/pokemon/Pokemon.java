package pokemon;

public class Pokemon {
    public enum Gender {MALE, FEMALE, NONE};

    private PokemonList pokemonKind;

    private String name;
    private Type type1, type2;
    // 종족값 | 0 ~ 5 까지의 인덱스가 존재하며 순서대로 HP, 공, 방, 특공, 특방, 스피드
    private int[] baseStats;
    // 특성
    private Ability ability;
    // 레벨
    private int level;
    // 성별
    private Gender gender;
    // 성격
    private Nature nature;
    // 개체치 | individual values. 0 ~ 31 의 값을 지님
    private int[] ivs;
    // 노력치 | effort values. 0 ~ 252 의 값을 지님
    private int[] evs;
    // 능력치 | 종족값, 개체값, 노력치를 바탕으로 계산된 실제 능력치
    private int[] stats;
    // 실능치 | 능력치에 특성이나 기술, 랭크 업다운 등으로 발생한 능력치의 변화가 적용된 각종 배틀 계산에 사용되는 실제 수치
    private int battleStats[];


    // 랭크 업다운 / -6 ~ 6 사이의 값, default : 0
    // 상승폭 : 150%, 200%, 250%, 300%, 350%, 400% |
    // 하락폭 : 66 %, 50 %, 40 %, 33 %, 29 %, 25 %


    // 배운 기술
    private MoveList[] moves = new MoveList[4];
    // 지닌 도구
    private Item item = null;


    // 상태 이상
    private Status status = null;
    private boolean isConfused = false;
    private int confuseTurnCount = -1;
    private boolean isStuned = false;




    public PokemonList getPokemonKind() {
        return pokemonKind;
    }

    public void setPokemonKind(PokemonList pokemonKind) {
        this.pokemonKind = pokemonKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public int[] getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(int[] baseStats) {
        this.baseStats = baseStats;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public int[] getIvs() {
        return ivs;
    }

    public void setIvs(int[] ivs) {
        this.ivs = ivs;
    }

    public int[] getEvs() {
        return evs;
    }

    public void setEvs(int[] evs) {
        this.evs = evs;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int[] getBattleStats() {
        return battleStats;
    }

    public void setBattleStats(int[] battleStats) {
        this.battleStats = battleStats;
    }

    public MoveList[] getMoves() {
        return moves;
    }

    public void setMoves(MoveList[] moves) {
        for (int i = 0; i < 4; i ++) {
            this.moves[i] = moves[i];
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isConfused() {
        return isConfused;
    }

    public void setConfused(boolean confused) {
        isConfused = confused;
    }

    public int getConfuseTurnCount() {
        return confuseTurnCount;
    }

    public void setConfuseTurnCount(int confuseTurnCount) {
        this.confuseTurnCount = confuseTurnCount;
    }

    public boolean isStuned() {
        return isStuned;
    }

    public void setStuned(boolean stuned) {
        isStuned = stuned;
    }

    // 능력치 계산
    // HP
    public static int caculateH(int level, int[] bs, int[] iv, int[] ev) {
        int stat;
        stat = (int) (((bs[0] * 2) + iv[0] + (ev[0] / 4)) * (level / 100.0f)) + 10 + level;
        return stat;
    }
    // 공, 방, 특공, 특방, 스피드
    public static int caculateABCDS(int kind, int level, int[] bs, int[] iv, int[] ev, Nature nature) {
        int stat;
        stat = (int) (((bs[kind] * 2) + iv[kind] + (ev[kind] / 4)) * (level / 100.0f)) + 5;

        // 무보정 성격일 경우 그대로 return
        if (nature.getUpStat() == nature.getDownStat()) {
            return stat;
        }

        // 성격에 따른 보정치 계산 | 상승치 : 1.1배, 하락치 : 0.9배
        if (kind == nature.getUpStat()) {
            stat = (int) (stat * 1.1f);
        } else if (kind == nature.getDownStat()) {
            stat = (int) (stat * 0.9f);
        }

        return stat;
    }
    // 초기화 함수
    public void initPokemon() {
        if (this.name == null) {
            this.name = pokemonKind.getName();
        }
        this.type1 = pokemonKind.getType1();
        this.type2 = pokemonKind.getType2();
        this.baseStats = pokemonKind.getBaseStats();
        this.stats = new int[]{
                caculateH(this.level, this.baseStats, this.ivs, this.evs),
                caculateABCDS(1, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(2, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(3, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(4, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(5, this.level, this.baseStats, this.ivs, this.evs, this.nature)
        };
    }
}
