package trainer.ai;

import trainer.Trainer;

public class WildAi extends BattleAi {
    // BattleAi의 기본 메서드 그대로 사용
    // 무조건 공격, 기술은 가진 기술중 랜덤으로 사용함
    public WildAi(Trainer trainer) {
        this.trainer = trainer;
        this.aiName = "wild";
    }
}
