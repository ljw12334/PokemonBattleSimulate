package pokemon;

public class Pokemon {
    public enum Gender {MALE, FEMALE, NONE};

    public PokemonList pokemonName;

    public String name;
    public Type type1, type2;
    // 종족값 | 0 ~ 5 까지의 인덱스가 존재하며 순서대로 HP, 공, 방, 특공, 특방, 스피드
    public int baseStats[];
    // 특성
    public Ability ability;
    // 레벨
    public int level;
    // 성별
    public Gender gender;
    // 성격
    public Nature nature;
    // 개체치 | individual values. 0 ~ 31 의 값을 지님
    public int ivs[];
    // 노력치 | effort values. 0 ~ 252 의 값을 지님
    public int evs[];
    // 능력치 | 종족값, 개체값, 노력치를 바탕으로 계산된 실제 능력치
    public int stats[];
    // 실능치 | 능력치에 특성이나 기술, 랭크 업다운 등으로 발생한 능력치의 변화가 적용된 각종 배틀 계산에 사용되는 실제 수치
    public int battleStats[];


    // 랭크 업다운 / -6 ~ 6 사이의 값, default : 0
    // 상승폭 : 150%, 200%, 250%, 300%, 350%, 400% |
    // 하락폭 : 66 %, 50 %, 40 %, 33 %, 29 %, 25 %


    // 배운 기술
    public Move moves[];
    // 지닌 도구
    public Item item = null;


    // 상태 이상
    public Status status = null;
    public boolean isConfused = false;
    public int confuseTurnCount = -1;
    public boolean isStuned = false;


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
        this.name = pokemonName.getName();
        this.type1 = pokemonName.getType1();
        this.type2 = pokemonName.getType2();
        this.baseStats = pokemonName.getBaseStats();
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
