package test;

import pokemon.Pokemon;
import pokemon.Type;
import pokemon.sample.CynthiaGarchomp;
import pokemon.sample.CynthiaLucario;

public class TypeTest {
    public static void main(String[] args) {
        Pokemon target = new CynthiaLucario();

        Type[] types = Type.values();

        float typeScore1;
        float typeScore2;

        System.out.println(target.getName() + "   [" + target.getType1().getNAME() + "][" + target.getType2().getNAME() + "]\n");
        for (Type attackT : types) {
            typeScore1 = typeScoreCaculate(attackT, target.getType1());
            typeScore2 = typeScoreCaculate(attackT, target.getType2());

            float result = typeScore1 * typeScore2;

            if (attackT.getNAME().length() == 1) {
                System.out.print(attackT.getNAME() + "　　 => ");
            } else if (attackT.getNAME().length() == 2) {
                System.out.print(attackT.getNAME() + "　 => ");
            } else {
                System.out.print(attackT.getNAME() + " => ");
            }

            if (result == 0) {
                System.out.println("효과가 없음　 (x" + result + ")");
            } else if (result > 1) {
                System.out.println("효과가 굉장함 (x" + result + ")");
            } else if (result < 1) {
                System.out.println("효과가 별로임 (x" + result + ")");
            } else {
                System.out.println("무상성 　　　 (x" + result + ")");
            }
        }
    }
    static float typeScoreCaculate(Type attackT, Type targetT1) {
        if (isSuperEffective(attackT, targetT1)) return 2;
        else if (isNotVeryEffective(attackT, targetT1)) return 0.5f;
        else if (isNoEffect(attackT, targetT1)) return 0;
        else return 1;
    }

    static boolean isSuperEffective(Type attackT, Type targetT) {
        for (String type : attackT.getSUPER_EFFECTIVE()) {
            if (type.equals(targetT.getNAME())) {
                return true;
            }
        }
        return false;
    }
    static boolean isNotVeryEffective(Type attackT, Type targetT) {
        for (String type : attackT.getNOT_VERY_EFFECTIVE()) {
            if (type.equals(targetT.getNAME())) {
                return true;
            }
        }
        return false;
    }
    static boolean isNoEffect(Type attackT, Type targetT) {
        for (String type : attackT.getNO_EFFECT()) {
            if (type.equals(targetT.getNAME())) {
                return true;
            }
        }
        return false;
    }
}
