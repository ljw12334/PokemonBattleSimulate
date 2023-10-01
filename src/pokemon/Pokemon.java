package pokemon;

public class Pokemon {
    public enum Gender {
        MALE("♂"), FEMALE("♀"), NONE(" ");

        private final String SYMBOL;
        Gender(String symbol) {
            SYMBOL = symbol;
        }
        public String getSYMBOL() { return SYMBOL; }
    }

    public enum Stat {
        HP((byte) 0), ATTACK((byte) 1), DEFENSE((byte) 2), SP_ATTACK((byte) 3), SP_DEFENSE((byte) 4), SPEED((byte) 5);

        private final byte ID;
        Stat(byte id) {
            ID = id;
        }
        public byte getID() { return ID; }
    }

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
    private int[] ivs = new int[6];
    // 노력치 | effort values. 0 ~ 252 의 값을 지님
    private int[] evs = new int[6];
    // 능력치 | 종족값, 개체값, 노력치를 바탕으로 계산된 실제 능력치
    private int[] stats = new int[6];
    // 실능치 | 능력치에 특성이나 기술, 랭크 업다운 등으로 발생한 능력치의 변화가 적용된 각종 배틀 계산에 사용되는 실제 수치
    private int[] battleStats = new int[6];


    // 랭크 업다운 / -6 ~ 6 사이의 값, default : 0
    // 상승폭 : 150%, 200%, 250%, 300%, 350%, 400% |
    // 하락폭 : 66 %, 50 %, 40 %, 33 %, 29 %, 25 %


    // 배운 기술
    private MoveList[] moves = new MoveList[4];
    private int[] pp;
    // 지닌 도구
    private Item item = null;


    // 상태 이상
    private Status status = null;
    private boolean isConfused = false;
    private int confuseTurnCount = -1;
    private boolean isStuned = false;


    public int[] getPp() {
        return pp;
    }

    public void setPp(int pp, int moveNum) {
        this.pp[moveNum] = pp;
    }

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

    public void setBaseStats(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.baseStats[Stat.HP.getID()] = hp;
        this.baseStats[Stat.ATTACK.getID()] = attack;
        this.baseStats[Stat.DEFENSE.getID()] = defense;
        this.baseStats[Stat.SP_ATTACK.getID()] = spAttack;
        this.baseStats[Stat.SP_DEFENSE.getID()] = spDefense;
        this.baseStats[Stat.SPEED.getID()] = speed;
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

    public void setIvs(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.ivs[Stat.HP.getID()] = hp;
        this.ivs[Stat.ATTACK.getID()] = attack;
        this.ivs[Stat.DEFENSE.getID()] = defense;
        this.ivs[Stat.SP_ATTACK.getID()] = spAttack;
        this.ivs[Stat.SP_DEFENSE.getID()] = spDefense;
        this.ivs[Stat.SPEED.getID()] = speed;
    }

    public int[] getEvs() {
        return evs;
    }

    public void setEvs(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.evs[Stat.ATTACK.getID()] = attack;
        this.evs[Stat.HP.getID()] = hp;
        this.evs[Stat.DEFENSE.getID()] = defense;
        this.evs[Stat.SP_ATTACK.getID()] = spAttack;
        this.evs[Stat.SP_DEFENSE.getID()] = spDefense;
        this.evs[Stat.SPEED.getID()] = speed;
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

    public void setCurrentHp(int hp) {
        this.battleStats[Stat.HP.getID()] = hp;
    }
    public void setBattleStats(int attack, int defense, int spAttack, int spDefense, int speed) {
        this.battleStats[Stat.ATTACK.getID()] = attack;
        this.battleStats[Stat.DEFENSE.getID()] = defense;
        this.battleStats[Stat.SP_ATTACK.getID()] = spAttack;
        this.battleStats[Stat.SP_DEFENSE.getID()] = spDefense;
        this.battleStats[Stat.SPEED.getID()] = speed;
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
    public static int caculateABCDS(Stat kind, int level, int[] bs, int[] iv, int[] ev, Nature nature) {
        int stat;
        stat = (int) (((bs[kind.getID()] * 2) + iv[kind.getID()] + (ev[kind.getID()] / 4)) * (level / 100.0f)) + 5;

        // 무보정 성격일 경우 그대로 return
        if (nature.getUpStat() == nature.getDownStat()) {
            return stat;
        }

        // 성격에 따른 보정치 계산 | 상승치 : 1.1배, 하락치 : 0.9배
        if (kind.getID() == nature.getUpStat()) {
            stat = (int) (stat * 1.1f);
        } else if (kind.getID() == nature.getDownStat()) {
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
                caculateABCDS(Stat.ATTACK, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(Stat.DEFENSE, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(Stat.SP_ATTACK, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(Stat.SP_DEFENSE, this.level, this.baseStats, this.ivs, this.evs, this.nature),
                caculateABCDS(Stat.SPEED, this.level, this.baseStats, this.ivs, this.evs, this.nature)
        };
        int[] pp;
        // 기술 개수
        if (this.getMoves().length >= 4) {
            pp = new int[]{ this.moves[0].getPp(), this.moves[1].getPp(),
                            this.moves[2].getPp(), this.moves[3].getPp() };
        } else if (this.getMoves().length >= 3) {
            pp = new int[]{ this.moves[0].getPp(), this.moves[1].getPp(),
                            this.moves[2].getPp() };
        } else if (this.getMoves().length >= 2) {
            pp = new int[]{ this.moves[0].getPp(), this.moves[1].getPp() };
        } else if (this.getMoves().length >= 1) {
            pp = new int[]{ this.moves[0].getPp() };
        } else {
            System.out.println("기술 없음");
            return;
        }
        this.pp = pp;
    }
    public boolean isNoPp() {
        if (this.moves.length == 4) {
            if (this.pp[0] == 0 && this.pp[1] == 0 && this.pp[2] == 0 && this.pp[3] == 0) {
                return true;
            }
        } else if (this.moves.length == 3) {
            if (this.pp[0] == 0 && this.pp[1] == 0 && this.pp[2] == 0) {
                return true;
            }
        } else if (this.moves.length == 2) {
            if (this.pp[0] == 0 && this.pp[1] == 0) {
                return true;
            }
        } else if (this.moves.length == 1) {
            if (this.pp[0] == 0) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
}
